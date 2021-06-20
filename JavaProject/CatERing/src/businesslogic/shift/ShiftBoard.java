package businesslogic.shift;

import businesslogic.task.Task;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ShiftBoard {
    private final ObservableList<Shift> assignedShifts;
    private static ShiftBoard instance;

    private ShiftBoard() {
        assignedShifts = Shift.getAllAssignedShifts();
    }

    public static ShiftBoard getInstance() {
        if (instance == null) instance = new ShiftBoard();

        return instance;
    }

    @Override
    public String toString() {
        String s = "TABELLONE TURNI\n";
        for (Shift sh : assignedShifts) {
            s += "\t- " + sh + "\n";
        }
        s += "\n";
        return s;
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
