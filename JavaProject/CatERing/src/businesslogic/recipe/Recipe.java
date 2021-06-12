package businesslogic.recipe;

import persistence.PersistenceManager;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private final String name;

    public Recipe(String name) {
        id = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Recipe> loadAllRecipes() {
        ArrayList<Recipe> result = new ArrayList<>();
        String query = "SELECT * FROM Recipes";
        PersistenceManager.executeQuery(query, rs -> {
            Recipe rec = new Recipe(rs.getString("name"));
            rec.id = rs.getInt("id");
            result.add(rec);
        });
        return result;
    }
}
