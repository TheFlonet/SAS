package businesslogic.recipe;

import java.util.List;

public class RecipeManager {
    private final List<Recipe> recipes;

    public RecipeManager() {
        recipes = Recipe.loadAllRecipes();
    }

    public Recipe[] getRecipes() {
        return this.recipes.toArray(new Recipe[recipes.size()]);
    }
}
