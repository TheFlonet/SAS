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

    public User getOwner() {
        return owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task createTask(KitchenProcess process) {
        return new Task(process);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTask(Task task, int pos) {
        tasks.add(pos, task);
    }
}
