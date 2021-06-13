package businesslogic.task;

import businesslogic.shift.Shift;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private SummarySheet currentSheet;
    private final List<TaskEventReceiver> eventReceivers;

    public TaskManager() {
        eventReceivers = new ArrayList<>();
    }

    public void addReceiver(TaskEventReceiver taskEventReceiver) {
        this.eventReceivers.add(taskEventReceiver);
    }

    public void removeReceiver(TaskEventReceiver taskEventReceiver) {
        this.eventReceivers.remove(taskEventReceiver);
    }

    private void notifyCreatedSummarySheet(SummarySheet summarySheet) {

    }

    private void notifyAssignedTask(Shift s, Task t) {

    }

    private void notifyEditedAssignment(Shift s, Task t) {

    }

    public void createSummarySheet(Service service) {

    }

    public void assignTask(Task t, Shift s, int time, int quantity) {
        assignTask(t, s, null, time, quantity);
    }

    public void assignTask(Task t, Shift s, Cook c, int time, int quantity) {

    }

    public void editAssignment(Task t, Cook newCook, Shift newShift) {
        editAssignment(t, newCook, newShift, 0, 0);
    }

    public void editAssignment(Task t, Cook newCook, Shift newShift, int time) {
        editAssignment(t, newCook, newShift, time, 0);
    }

    public void editAssignment(Task t, Cook newCook, Shift newShift, int quantity) {
        editAssignment(t, newCook, newShift, 0, quantity);
    }

    public void editAssignment(Task t, Cook newCook, Shift newShift, int time, int quantity) {

    }
}
