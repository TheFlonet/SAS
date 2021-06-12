package businesslogic;

import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private final MenuManager menuMgr;
    private final RecipeManager recipeMgr;
    private final UserManager userMgr;

    private MenuPersistence menuPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        menuPersistence = new MenuPersistence();
        menuMgr.addEventReceiver(menuPersistence);
    }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }
}
