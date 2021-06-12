package businesslogic.task;

public interface TaskEventReceiver {
    void updateCreatedSummarySheet(SummarySheet summarySheet);
    void updateAssignedTask(Task t, Cook c, int time, int quantity);
    void updateEditedAssignment(Shift s, Task t);
}
