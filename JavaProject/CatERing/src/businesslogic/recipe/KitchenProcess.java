package businesslogic.recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class KitchenProcess {
    private static Map<Integer, KitchenProcess> all = new HashMap<>();


    private int id;
    private String name;

    private KitchenProcess() {}

    public KitchenProcess(String name) {
        id = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ObservableList<KitchenProcess> loadAllKitchenProcesses() {
        String query = "SELECT * FROM KitchenProcesses";
        PersistenceManager.executeQuery(query, rs -> {
            int id = rs.getInt("id");
            if (all.containsKey(id)) {
                KitchenProcess rec = all.get(id);
                rec.name = rs.getString("name");
            } else {
                KitchenProcess rec = new KitchenProcess(rs.getString("name"));
                rec.id = id;
                all.put(rec.id, rec);
            }
        });

        ObservableList<KitchenProcess> ret =  FXCollections.observableArrayList(all.values());
        ret.sort(Comparator.comparing(KitchenProcess::getName));
        return ret;
    }

    public static ObservableList<KitchenProcess> getAllKitchenProcesses() {
        return FXCollections.observableArrayList(all.values());
    }

    public static KitchenProcess loadKitchenProcessById(int id) {
        if (all.containsKey(id)) return all.get(id);
        final KitchenProcess rec = new KitchenProcess();
        String query = "SELECT * FROM KitchenProcesses WHERE id = " + id;
        PersistenceManager.executeQuery(query, rs -> {
            rec.name = rs.getString("name");
            rec.id = id;
            all.put(rec.id, rec);
        });
        return rec;
    }
}
