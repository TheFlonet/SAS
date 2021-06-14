package businesslogic.recipe;

import java.util.List;

public class RecipeManager {
    private final List<KitchenProcess> kitchenProcesses;

    public RecipeManager() {
        kitchenProcesses = KitchenProcess.loadAllRecipes();
    }

    public KitchenProcess[] getKitchenProcesses() {
        return this.kitchenProcesses.toArray(new KitchenProcess[kitchenProcesses.size()]);
    }
}
