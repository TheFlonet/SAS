package businesslogic.task;

import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Task {

    private static final Map<Integer, Task> loadedTasks = new HashMap<>();

    private int id;
    private final KitchenProcess process;
    private long time;
    private int quantity;
    private User cook;

    public Task(KitchenProcess kitchenProcess) {
        id = 0;
        process = kitchenProcess;
        time = 0;
        quantity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "\t- " + process.getName() + " (id " + id + "); cuoco = " + (cook != null ? cook.getUserName() : "unavailable");
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<Task> getTasksFor(int shift_id) {
        ObservableList<Task> res = FXCollections.observableArrayList();
        String query = "SELECT * FROM tasks WHERE shift_id = " + shift_id;
        PersistenceManager.executeQuery(query, rs -> {
            int task_id = rs.getInt("id");
            if (loadedTasks.containsKey(task_id))
                res.add(loadedTasks.get(task_id));
            else {
                KitchenProcess process = KitchenProcess.loadKitchenProcessById(rs.getInt("process_id"));
                Task task = new Task(process);
                task.id = task_id;
                int cook_id;
                if((cook_id = rs.getInt("cook_id")) > 0) task.cook = User.loadUserById(cook_id);
                task.quantity = rs.getInt("qty");
                task.time = rs.getInt("time");
                loadedTasks.put(task_id, task);
                res.add(task);
            }
        });

        return res;
    }
}
