package businesslogic.recipe;

import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class KitchenProcess {
    private int id;
    private final String name;

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

    public static List<KitchenProcess> loadAllRecipes() {
        List<KitchenProcess> result = new ArrayList<>();
        String query = "SELECT * FROM Recipes";
        PersistenceManager.executeQuery(query, rs -> {
            KitchenProcess rec = new KitchenProcess(rs.getString("name"));
            rec.id = rs.getInt("id");
            result.add(rec);
        });
        return result;
    }
}
