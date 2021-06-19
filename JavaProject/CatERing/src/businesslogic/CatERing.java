package businesslogic;

import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.shift.ShiftManager;
import businesslogic.task.TaskManager;
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
    private final ShiftManager shiftMgr;
    private final TaskManager taskMgr;

    private MenuPersistence menuPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        shiftMgr = new ShiftManager();
        taskMgr = new TaskManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        menuMgr.addEventReceiver(menuPersistence);
        // TODO: creare TaskPersistece e aggiungerlo ai listener di taskMgr
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

    public ShiftManager getShiftManager() {
        return shiftMgr;
    }

    public TaskManager getTaskManager() { return taskMgr; }
}
