package businesslogic.event;

import businesslogic.menu.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;

import java.sql.Date;
import java.sql.Time;

public class Service {
    private int id;
    private Menu currentMenu;
    private final String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;

    public Service(String name) {
        this.name = name;
    }

    public Menu getCurrentMenu() { return currentMenu; }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<Service> loadServiceInfoForEvent(int event_id) {
        ObservableList<Service> result = FXCollections.observableArrayList();
        String query = "SELECT id, name, approved_menu_id, service_date, time_start, time_end, expected_participants " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, rs -> {
            String s = rs.getString("name");
            Service serv = new Service(s);
            serv.id = rs.getInt("id");
            serv.currentMenu = Menu.getMenuByID(rs.getInt("approved_menu_id"));
            serv.date = rs.getDate("service_date");
            serv.timeStart = rs.getTime("time_start");
            serv.timeEnd = rs.getTime("time_end");
            serv.participants = rs.getInt("expected_participants");
            result.add(serv);
        });

        return result;
    }

    @Override
    public String toString() {
        return "Servizio \"" + name + "\" (id " + id + ")";
    }
}
