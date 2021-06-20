package businesslogic.shift;

import businesslogic.task.Task;
import businesslogic.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public abstract class Shift {

    private static final Map<Integer, Shift> loadedShifts = new HashMap<>();

    protected int id;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected boolean isComplete;
    protected ObservableList<Task> assignedTasks;
    protected ObservableList<User> assignedCooks;
    protected LocalDate deadlineEditAvailability;
    protected Type type;

    public Shift(LocalDate date, LocalTime startTime, LocalTime endTime) {
        id = 0;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        isComplete = false;
        assignedTasks = FXCollections.observableArrayList();
        assignedCooks = FXCollections.observableArrayList();
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isComplete=" + isComplete +
                ", assignedTasks=" + assignedTasks +
                ", assignedCooks=" + assignedCooks +
                ", deadlineEditAvailability=" + deadlineEditAvailability +
                ", type=" + type +
                '}';
    }

    public void  addTask(Task t) {
        assignedTasks.add(t);
    }

    public void removeTask(Task t) {
        assignedTasks.remove(t);
    }

    public int getId() { return id; }

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

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<Shift> getAllShifts() {
        return FXCollections.observableArrayList(loadedShifts.values());
    }

    public static ObservableList<Shift> getAllAssignedShifts() {
        List<Shift> res = new ArrayList<>();
        for(Shift v : loadedShifts.values()) {
            if(v.getAssignedTasks().size() > 0)
                res.add(v);
        }

        return FXCollections.observableList(res);
    }

    public static ObservableList<Shift> loadAllShifts() {
        String query = "SELECT * FROM shifts";
        PersistenceManager.executeQuery(query, rs -> {
            int id = rs.getInt("id");
            Shift rec;
            if (!loadedShifts.containsKey(id)) {
                String type = rs.getString("type");
                LocalDate date = rs.getObject("date", LocalDate.class);
                LocalTime startTime = rs.getObject("startTime", LocalTime.class);
                LocalTime endTime = rs.getObject("endTime", LocalTime.class);
                boolean isComplete = rs.getBoolean("is_complete");

                if ("prep".equals(type))
                    rec = new PreparatoryShift(date, startTime, endTime);
                else
                    throw new IllegalStateException();

                rec.id = id;
                rec.isComplete = isComplete;
                rec.assignedCooks = User.getCooksFor(rec.id);
                rec.assignedTasks = Task.getTasksFor(rec.id);

                loadedShifts.put(rec.id, rec);
            }
        });

        ObservableList<Shift> ret =  FXCollections.observableArrayList(loadedShifts.values());
        ret.sort(Comparator.comparing(Shift::getDate));
        return ret;
    }
}
