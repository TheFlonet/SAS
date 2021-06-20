package businesslogic.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import java.util.*;

public class User {

    private static final Map<Integer, User> loadedUsers = FXCollections.observableHashMap();

    public enum Role {SERVIZIO, CUOCO, CHEF, ORGANIZZATORE};

    private int id;
    private String username;
    private final Set<Role> roles;

    public User() {
        id = 0;
        username = "";
        this.roles = new HashSet<>();
    }

    @Override
    public String toString() {
        return username + " (id " + id + "); ruoli=" + roles;
    }

    public boolean isChef() {
        return roles.contains(Role.CHEF);
    }

    public boolean isCook() { return roles.contains(Role.CUOCO); }

    public String getUserName() {
        return username;
    }

    public int getId() {
        return this.id;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static User loadUserById(int uid) {
        if (loadedUsers.containsKey(uid)) return loadedUsers.get(uid);

        User load = new User();
        String userQuery = "SELECT * FROM Users WHERE id='"+uid+"'";
        PersistenceManager.executeQuery(userQuery, rs -> {
            load.id = rs.getInt("id");
            load.username = rs.getString("username");
        });
        if (load.id > 0) {
            loadedUsers.put(load.id, load);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + load.id;
            PersistenceManager.executeQuery(roleQuery, rs -> {
                String role = rs.getString("role_id");
                switch (role.charAt(0)) {
                    case 'c' -> load.roles.add(Role.CUOCO);
                    case 'h' -> load.roles.add(Role.CHEF);
                    case 'o' -> load.roles.add(Role.ORGANIZZATORE);
                    case 's' -> load.roles.add(Role.SERVIZIO);
                }
            });
        }
        return load;
    }

    public static User loadUser(String username) {
        User u = new User();
        String userQuery = "SELECT * FROM Users WHERE username='"+username+"'";
        PersistenceManager.executeQuery(userQuery, rs -> {
            u.id = rs.getInt("id");
            u.username = rs.getString("username");
        });
        if (u.id > 0) {
            loadedUsers.put(u.id, u);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + u.id;
            PersistenceManager.executeQuery(roleQuery, rs -> {
                String role = rs.getString("role_id");
                switch (role.charAt(0)) {
                    case 'c' -> u.roles.add(Role.CUOCO);
                    case 'h' -> u.roles.add(Role.CHEF);
                    case 'o' -> u.roles.add(Role.ORGANIZZATORE);
                    case 's' -> u.roles.add(Role.SERVIZIO);
                }
            });
        }
        return u;
    }

    public static ObservableList<User> getCooksFor(int shift_id) {
        ObservableList<User> res = FXCollections.observableArrayList();
        String query = "SELECT cook_id FROM shiftcooks WHERE shift_id = " + shift_id;
        PersistenceManager.executeQuery(query, rs -> {
            int cook_id = rs.getInt("cook_id");
            if (loadedUsers.containsKey(cook_id)) res.add(loadedUsers.get(cook_id));
            else res.add(loadUserById(cook_id));
        });

        res.sort(Comparator.comparing(User::getId));
        return res;
    }

    public static ObservableList<User> getAllCooks() {
        ObservableList<User> res = FXCollections.observableArrayList();
        String query = "SELECT user_id FROM userroles WHERE role_id = 'c'";
        PersistenceManager.executeQuery(query, rs -> {
            int cook_id = rs.getInt("user_id");
            if (loadedUsers.containsKey(cook_id)) res.add(loadedUsers.get(cook_id));
            else res.add(loadUserById(cook_id));
        });

        res.sort(Comparator.comparing(User::getId));
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }
}
