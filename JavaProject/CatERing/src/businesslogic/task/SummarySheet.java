package businesslogic.task;

import businesslogic.event.Service;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;
import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class SummarySheet {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Service getAssociatedService() {
        return associatedService;
    }

    public Task createTask(KitchenProcess process) {
        Task t = new Task(process);
        if(!tasks.contains(t)) tasks.add(t);
        else t = null;
        return t;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTask(Task task, int pos) {
        tasks.add(pos, task);
    }

    @Override
    public String toString() {
        String s = "";
        for(Task t : tasks) {
            s += "\t\t" + t + "\n";
        }
        return "\nFOGLIO RIEPILOGATIVO CORRENTE (id " + id + "):\n" +
                "\tCOMPITI:\n" + s +
                "\n\tSERVIZIO ASSOCIATO: " + associatedService +
                "\n\tPROPRIETARIO: " + owner + "\n";
    }

    // STATIC METHODS FOR PERSISTENCE
    public static void saveNewSummarySheet(SummarySheet summarySheet) {
        String sql = "INSERT INTO summarysheets (service_id, creator_id) VALUES (" + summarySheet.associatedService.getId() + "," + summarySheet.owner.getId() + ");";
        PersistenceManager.executeUpdate(sql);
        summarySheet.id = PersistenceManager.getLastId();
    }

}
