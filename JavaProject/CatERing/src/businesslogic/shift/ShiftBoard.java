package businesslogic.shift;

import businesslogic.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ShiftBoard {
    private final List<Shift> assignedShifts;
    private static ShiftBoard instance;

    private ShiftBoard() {
        assignedShifts = new ArrayList<>();
    }

    public static ShiftBoard getInstance() {
        if (instance == null) instance = new ShiftBoard();

        return instance;
    }

    public Shift findShiftOf(Task task) {
        Shift shift = null;

        for (Shift s : assignedShifts) {
            int index = s.assignedTasks.indexOf(task);
            if (index > -1) shift = s;
        }

        return shift;
    }

    public List<Shift> getAssignedShifts() {
        return assignedShifts;
    }

    public void addShift(Shift shift) {
        assignedShifts.add(shift);
    }

    public void removeShift(Shift shift) {
        assignedShifts.remove(shift);
    }
}
