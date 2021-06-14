package businesslogic.task;

import businesslogic.CatERing;
import businesslogic.PastShiftException;
import businesslogic.UnavailableCookException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Service;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.Shift;
import businesslogic.shift.ShiftBoard;
import businesslogic.user.User;

import java.util.ArrayList;
import java.util.Date;
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
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateAssignedTask(s, t);
    }

    private void notifyEditedAssignment(Shift s, Task t) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateEditedAssignment(s, t);
    }

    private void notifySetSummarySheet(SummarySheet summarySheet) { //TODO capire se serve il parametro
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateSetSummarySheet(currentSheet);
    }

    private void notifyAddedTask(Task task) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateAddedTask(task);
    }

    private void notifyRemovedTask(Task task) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateRemovedTask(task);
    }

    private void notifyMovedTask(Task task, int pos) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateMovedTask(task, pos);
    }

    private void notifyRemovedAssignment(Task task) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateRemovedAssignment(task);
    }

    private void notifySpecificationsAdded(Task task, User cook) {
    }

    private void notifySpecificationsAdded(Task task, long time) {
    }

    private void notifySpecificationsAdded(Task task, int quantity) {
    }

    private void notifySpecificationsAdded(Task task, User cook, long time) {
    }

    private void notifySpecificationsAdded(Task task, User cook, int quantity) {
    }

    private void notifySpecificationsAdded(Task task, long time, int quantity) {
    }
    //TODO servono tutti i casi o basta quello con tutti i parametri?

    private void notifySpecificationsAdded(Task task, User cook, long time, int quantity) {
        for (TaskEventReceiver receiver : eventReceivers)
            receiver.updateSpecificationsAdded(task, cook, time, quantity);
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

    public void assignTask(Task task, Shift shift, User cook) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, cook, 0, 0);
    }

    public void assignTask(Task task, Shift shift, long time) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, null, time, 0);
    }

    public void assignTask(Task task, Shift shift, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, null, 0, quantity);
    }

    public void assignTask(Task task, Shift shift, User cook, long time) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, cook, time, 0);
    }

    public void assignTask(Task task, Shift shift, User cook, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, cook, 0, quantity);
    }

    public void assignTask(Task task, Shift shift, long time, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        assignTask(task, shift, null, time, quantity);
    }

    public void assignTask(Task task, Shift shift, User cook, long time, int quantity) throws UseCaseLogicException, PastShiftException, UnavailableCookException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        ShiftBoard board = CatERing.getInstance().getShiftManager().getShiftBoard();

        if (!(user.isChef()
                && currentSheet != null
                && currentSheet.getOwner().equals(user)
                && currentSheet.getTasks().contains(task)))
            throw new UseCaseLogicException();

        if (shift.getDate().before(new Date())) throw new PastShiftException(); //TODO è giusto SHIFT?

        if (cook != null && !shift.getAssignedCooks().contains(cook)) throw new UnavailableCookException();

        shift.addTask(task);

        if (cook != null) task.setCook(cook);
        if (time > 0) task.setTime(time);
        if (quantity > 0) task.setQuantity(quantity);

        notifyAssignedTask(shift, task);
    }

    public void editAssignment(Task task, Shift newShift, User newCook) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, newCook, 0, 0);
    }

    public void editAssignment(Task task, Shift newShift, long time) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, null, time, 0);
    }

    public void editAssignment(Task task, Shift newShift, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, null, 0, quantity);
    }

    public void editAssignment(Task task, Shift newShift, User newCook, long time) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, newCook, time, 0);
    }

    public void editAssignment(Task task, Shift newShift, User newCook, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, newCook, 0, quantity);
    }

    public void editAssignment(Task task, Shift newShift, long time, int quantity) throws PastShiftException, UnavailableCookException, UseCaseLogicException {
        editAssignment(task, newShift, null, time, quantity);
    }

    public void editAssignment(Task task, Shift newShift, User newCook, long time, int quantity) throws UseCaseLogicException, UnavailableCookException, PastShiftException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        ShiftBoard board = getShiftBoard();
        Shift shift = CatERing.getInstance().getShiftManager().getShiftOf(task);

        if (!(currentSheet != null
                && currentSheet.getOwner().equals(user)
                && user.isChef()
                && currentSheet.getTasks().contains(task)
                && shift != null
                && shift.getDate().after(new Date())
                && board.getAssignedShifts().contains(shift)
                && !newShift.equals(shift)))
            throw new UseCaseLogicException();

        if (newCook != null
                && !(newCook.isCook()
                && newShift.getAssignedCooks().contains(newCook)))
            throw new UnavailableCookException();

        if (newShift.getDate().before(new Date())) throw new PastShiftException();

        shift.removeTask(task);

        if (shift.getAssignedTasks().size() == 0) board.removeShift(shift);

        newShift.addTask(task);

        if (newCook != null) task.setCook(newCook);
        if (time > 0) task.setTime(time);
        if (quantity > 0) task.setQuantity(quantity);

        if (!board.getAssignedShifts().contains(newShift)) board.addShift(newShift); //TODO shift o newShift?

        notifyEditedAssignment(newShift, task);
    }

    public SummarySheet setCurrentSheet(SummarySheet summarySheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) throw new UseCaseLogicException("You are not logged in as a chef");

        currentSheet = summarySheet;
        notifySetSummarySheet(summarySheet);

        return currentSheet;
    }

    public void addTaskToSheet(KitchenProcess kitchenProcess) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) throw new UseCaseLogicException("You are not logged in as a chef");

        Task task = currentSheet.createTask(kitchenProcess);

        notifyAddedTask(task);
    }

    public void removeTaskFromSheet(Task task) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!(user.isChef() && currentSheet != null
                && currentSheet.getOwner().equals(user)
                && currentSheet.getTasks().contains(task)))
            throw new UseCaseLogicException();

        currentSheet.removeTask(task);

        notifyRemovedTask(task);
    }

    public void moveTask(Task task, int pos) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!(user.isChef()
                && currentSheet != null
                && currentSheet.getOwner().equals(user)))
            throw new UseCaseLogicException();

        currentSheet.removeTask(task);
        currentSheet.addTask(task, pos);

        notifyMovedTask(task, pos);
    }

    public ShiftBoard getShiftBoard() throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef()) throw new UseCaseLogicException("You are not logged in as a chef");

        return CatERing.getInstance().getShiftManager().getShiftBoard();
    }

    public void removeAssignment(Task task) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        ShiftBoard board = CatERing.getInstance().getShiftManager().getShiftBoard();
        Shift shift = CatERing.getInstance().getShiftManager().getShiftOf(task);

        if (!(currentSheet != null
                && currentSheet.getOwner().equals(user)
                && user.isChef()
                && currentSheet.getTasks().contains(task)
                && shift != null
                && shift.getDate().after(new Date())
                && board.getAssignedShifts().contains(shift)))
            throw new UseCaseLogicException();

        shift.removeTask(task);
        //TODO cook = null, cosa intendevamo?

        if (shift.getAssignedTasks().size() == 0) board.removeShift(shift);

        notifyRemovedAssignment(task);
    }

    public void addSpecification(Task task, User newCook) throws UseCaseLogicException {
        addSpecification(task, newCook, 0, 0);
    }

    public void addSpecification(Task task, long time) throws UseCaseLogicException {
        addSpecification(task, null, time, 0);
    }

    public void addSpecification(Task task, int quantity) throws UseCaseLogicException {
        addSpecification(task, null, 0, quantity);
    }

    public void addSpecification(Task task, User newCook, long time) throws UseCaseLogicException {
        addSpecification(task, newCook, time, 0);
    }

    public void addSpecification(Task task, User newCook, int quantity) throws UseCaseLogicException {
        addSpecification(task, newCook, 0, quantity);
    }

    public void addSpecification(Task task, long time, int quantity) throws UseCaseLogicException {
        addSpecification(task, null, time, quantity);
    }

    public void addSpecification(Task task, User newCook, long time, int quantity) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        Shift shift = null;

        if (newCook != null) shift = CatERing.getInstance().getShiftManager().getShiftOf(task);

        if (!(currentSheet != null
                && currentSheet.getOwner().equals(user)
                && user.isChef()
                && currentSheet.getTasks().contains(task)))
            throw new UseCaseLogicException();

        if (newCook != null
                && !(shift != null
                && shift.getDate().after(new Date())
                && shift.getAssignedCooks().contains(newCook)))
            throw new UseCaseLogicException();

        if (newCook != null) task.setCook(newCook);
        if (time > 0) task.setTime(time);
        if (quantity > 0) task.setQuantity(quantity);

        notifySpecificationsAdded(task, newCook, time, quantity);
    }
}
