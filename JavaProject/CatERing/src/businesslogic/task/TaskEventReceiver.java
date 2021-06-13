package businesslogic.task;

import businesslogic.shift.Shift;
import businesslogic.user.User;

public interface TaskEventReceiver {
    void updateCreatedSummarySheet(SummarySheet summarySheet); //TODO la firma è giusta (?)
    void updateSetSummarySheet(SummarySheet summarySheet); //TODO la firma è giusta (?)
    void updateAssignedTask(Shift s, Task t);
    void updateEditedAssignment(Shift s, Task t);
    void updateAddedTask(Task task);
    void updateRemovedTask(Task task);
    void updateMovedTask(Task task, int pos);
    void updateRemovedAssignment(Task task);
    void updateSpecificationsAdded(Task task);
    void updateSpecificationsAdded(Task task, User cook);
    void updateSpecificationsAdded(Task task, User cook, long time);
    void updateSpecificationsAdded(Task task, User cook, int quantity);
    void updateSpecificationsAdded(Task task, long time);
    void updateSpecificationsAdded(Task task, long time, int quantity);
    void updateSpecificationsAdded(Task task, int quantity);
    void updateSpecificationsAdded(Task task, User cook, long time, int quantity);
}