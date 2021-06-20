package businesslogic.shift;

import businesslogic.task.Task;
import businesslogic.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Shift {
    protected int id;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected boolean isComplete;
    protected List<Task> assignedTasks;
    protected List<User> assignedCooks;
    protected LocalDate deadlineEditAvailability;
    protected Type type;

    public Shift(LocalDate date, LocalTime startTime, LocalTime endTime) {
        id = 0;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        isComplete = false;
        assignedTasks = new ArrayList<>();
        assignedCooks = new ArrayList<>();
    }

    public void  addTask(Task t) {
        assignedTasks.add(t);
    }

    public void removeTask(Task t) {
        assignedTasks.remove(t);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDeadlineEditAvailability() {
        return deadlineEditAvailability;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDeadlineEditAvailability(LocalDate deadlineEditAvailability) {
        this.deadlineEditAvailability = deadlineEditAvailability;
    }

    public List<User> getAssignedCooks() {
        return assignedCooks;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public abstract Type getType();
}
