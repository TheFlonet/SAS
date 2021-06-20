package persistence;

import businesslogic.shift.Shift;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.TaskEventReceiver;
import businesslogic.user.User;

public class TaskPersistence implements TaskEventReceiver {

    @Override
    public void updateCreatedSummarySheet(SummarySheet summarySheet) {
        SummarySheet.saveNewSummarySheet(summarySheet);
    }

    @Override
    public void updateSetSummarySheet(SummarySheet summarySheet) {}

    @Override
    public void updateAssignedTask(Shift s, Task t) {
        Task.saveAssignedTask(s, t);
    }

    @Override
    public void updateEditedAssignment(Shift s, Task t) {}

    @Override
    public void updateAddedTask(SummarySheet s, Task task) {
        Task.saveNewTask(s, task);
    }

    @Override
    public void updateRemovedTask(SummarySheet s, Task task) {
        Task.saveRemovedTask(s, task);
    }

    @Override
    public void updateMovedTask(Task task, int pos) {
        // nessun salvataggio relativo a posizione
    }

    @Override
    public void updateRemovedAssignment(Task task) {
        Task.saveRemovedAssignment(task);
    }

    @Override
    public void updateSpecificationsAdded(Task task, User cook, long time, int quantity) {
        Task.saveNewSpecs(task, cook, time, quantity);
    }
}
