package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.KitchenProcessManager;
import businesslogic.shift.ShiftManager;
import businesslogic.task.TaskManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;
import persistence.TaskPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private final MenuManager menuMgr;
    private final KitchenProcessManager kitchenProcessMgr;
    private final UserManager userMgr;
    private final ShiftManager shiftMgr;
    private final TaskManager taskMgr;
    private final EventManager eventMgr;

    private MenuPersistence menuPersistence;
    private TaskPersistence taskPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        kitchenProcessMgr = new KitchenProcessManager();
        userMgr = new UserManager();
        shiftMgr = new ShiftManager();
        taskMgr = new TaskManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        menuMgr.addEventReceiver(menuPersistence);
        taskPersistence = new TaskPersistence();
        taskMgr.addReceiver(taskPersistence);
    }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public KitchenProcessManager getKitchenProcessManager() {
        return kitchenProcessMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public ShiftManager getShiftManager() {
        return shiftMgr;
    }

    public TaskManager getTaskManager() { return taskMgr; }

    public EventManager getEventManager() { return eventMgr; }
}
