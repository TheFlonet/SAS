package businesslogic.task;

import java.util.ArrayList;
import java.util.List;

public class SummarySheet {
    private final List<Task> tasks;
    private final Service associatedService;

    public SummarySheet() {
        tasks = new ArrayList<>();
    }

    public void addAssignment(Task t, Shift s, int time, int quantity) {
        addAssignment(t, s, null, time, quantity);
    }

    public void addAssignment(Task t, Shift s, Cook c, int time, int quantity) {

    }

    public void initSectionItems() {

    }

    public void initFreeItems() {

    }
}
