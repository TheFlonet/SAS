package businesslogic.task;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Service;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.Shift;
import businesslogic.shift.ShiftBoard;
import businesslogic.user.User;

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

    private void notifyCreatedSummarySheet(SummarySheet summarySheet) { //TODO capire se serve il parametro
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateCreatedSummarySheet(currentSheet);
    }

    private void notifyAssignedTask(Shift s, Task t) {
//TODO
    }

    private void notifyEditedAssignment(Shift s, Task t) {
//TODO
    }

    private void notifySetSummarySheet(SummarySheet summarySheet) { //TODO capire se serve il parametro
        for (TaskEventReceiver receiver : eventReceivers) {
            receiver.updateSetSummarySheet(currentSheet);
        }
    }

    private void notifyAddedTask(Task task) {
        //TODO
    }

    private void notifyRemovedTask(Task task) {
        //TODO
    }

    private void notifyMovedTask(Task task, int pos) {
        //TODO
    }

    private void notifyRemovedAssignment(Task task) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, User cook) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, long time) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, int quantity) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, User cook, long time) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, User cook, int quantity) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, long time, int quantity) {
        //TODO
    }

    private void notifySpecificationsAdded(Task task, User cook, long time, int quantity) {
        //TODO
    }

    public SummarySheet createSummarySheet(Service service) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!(user.isChef() && service.getCurrentMenu() != null)) throw new UseCaseLogicException();

        currentSheet = new SummarySheet(service, user);
        currentSheet.initSectionItems();
        currentSheet.initFreeItems();
        notifyCreatedSummarySheet(currentSheet);

        return currentSheet;
    }

    public void assignTask(Task task, Shift shift, User cook) {
        //TODO
    }

    public void assignTask(Task task, Shift shift, long time) {
        //TODO
    }

    public void assignTask(Task task, Shift shift, int quantity) {
        //TODO
    }

    public void assignTask(Task task, Shift shift, User cook, long time) {
        //TODO
    }

    public void assignTask(Task task, Shift shift, User cook, int quantity) {
        //TODO
    }

    public void assignTask(Task task, Shift shift, long time, int quantity) {
        //TODO
    }

    public void assignTask(Task task, Shift newShift, User cook, long time, int quantity) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, User newCook) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, long time) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, int quantity) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, User newCook, long time) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, User newCook, int quantity) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, long time, int quantity) {
        //TODO
    }

    public void editAssignment(Task task, Shift newShift, User newCook, long time, int quantity) {
        //TODO
    }

    public SummarySheet setCurrentSheet(SummarySheet summarySheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef())
            throw new UseCaseLogicException("You are not logged in as a chef!");

        currentSheet = summarySheet;
        notifySetSummarySheet(summarySheet);

        return currentSheet;
    }

    public void addTaskToSheet(KitchenProcess kitchenProcess) {
        //TODO
    }

    public void removeTaskFromSheet(Task task) {
        //TODO
    }

    public void moveTask(Task task, int pos) {
        //TODO
    }

    public ShiftBoard getShiftBoard() {
        //TODO
        return null;
    }

    public void removeAssignment(Task task) {
        //TODO
    }

    public void addSpecification(Task task, User newCook) {
        //TODO
    }

    public void addSpecification(Task task, long time) {
        //TODO
    }

    public void addSpecification(Task task, int quantity) {
        //TODO
    }

    public void addSpecification(Task task, User newCook, long time) {
        //TODO
    }

    public void addSpecification(Task task, User newCook, int quantity) {
        //TODO
    }

    public void addSpecification(Task task, long time, int quantity) {
        //TODO
    }

    public void addSpecification(Task task, User newCook, long time, int quantity) {
        //TODO
    }
}
