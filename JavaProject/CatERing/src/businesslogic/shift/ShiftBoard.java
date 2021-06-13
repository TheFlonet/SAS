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
        //TODO
        return null;
    }

    public void addShift(Shift shift) {
        //TODO
    }

    public void removeShift(Shift shift) {
        //TODO
    }
}
