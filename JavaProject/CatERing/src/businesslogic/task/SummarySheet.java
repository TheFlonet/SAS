package businesslogic.task;

import businesslogic.event.Service;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;

import java.util.ArrayList;
import java.util.List;

public class SummarySheet {
    private final List<Task> tasks;
    private final Service associatedService;
    private User owner;

    public SummarySheet(Service associatedService, User owner) {
        this.owner = owner;
        this.associatedService = associatedService;
        tasks = new ArrayList<>();
    }

    public void initSectionItems() {
        for (Section section : associatedService.getCurrentMenu().getSections()) {
            for (MenuItem menuItem : section.getSectionItems()) {
                KitchenProcess process = menuItem.getItemProcess();
                tasks.add(new Task(process));
            }
        }
    }

    public void initFreeItems() {
        for (MenuItem menuItem : associatedService.getCurrentMenu().getFreeItems()) {
            KitchenProcess process = menuItem.getItemProcess();
            tasks.add(new Task(process));
        }
    }

    public void setOwner(User user) {
        owner = user;
    }

    public void createTask(KitchenProcess process) {
        //TODO
    }

    public void removeTask(Task task) {
        //TODO
    }

    public void addTask(Task task, int pos) {
        //TODO
    }
}
