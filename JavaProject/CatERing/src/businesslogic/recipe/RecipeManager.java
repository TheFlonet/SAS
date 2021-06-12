package businesslogic.recipe;

import java.util.ArrayList;

public class RecipeManager {
    private final ArrayList<Recipe> recipes;

    public RecipeManager() {
        recipes = Recipe.loadAllRecipes();
    }

    public Recipe[] getRecipes() {
        return this.recipes.toArray(new Recipe[recipes.size()]);
    }
}
