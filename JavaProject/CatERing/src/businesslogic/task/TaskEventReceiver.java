package businesslogic.task;

import businesslogic.shift.Shift;
import businesslogic.user.User;

public interface TaskEventReceiver {
    void updateCreatedSummarySheet(SummarySheet summarySheet);
    void updateSetSummarySheet(SummarySheet summarySheet);
    void updateAssignedTask(Shift s, Task t);
    void updateEditedAssignment(Shift s, Task t);
    void updateAddedTask(Task task);
    void updateRemovedTask(Task task);
    void updateMovedTask(Task task, int pos);
    void updateRemovedAssignment(Task task);
    void updateSpecificationsAdded(Task task, User cook, long time, int quantity);
}