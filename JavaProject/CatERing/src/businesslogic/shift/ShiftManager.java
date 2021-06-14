package businesslogic.shift;

import businesslogic.task.Task;

public class ShiftManager {
    private final ShiftBoard board;

    public ShiftManager() {
        board = ShiftBoard.getInstance();
    }

    public ShiftBoard getShiftBoard() {
        return board;
    }

    public Shift getShiftOf(Task task) {
        return board.findShiftOf(task);
    }
}
