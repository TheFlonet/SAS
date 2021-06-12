package businesslogic.shift;

import businesslogic.task.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Shift {
    protected Date date;
    protected double startTime;
    protected double endTime;
    protected List<Task> assignedTasks;
    protected Cook assignedCook;

    public Shift() {
        assignedTasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        assignedTasks.add(t);
    }

    public void removeTask(Task t) {
        assignedTasks.remove(t);
    }

    public abstract String getLocation();
}
