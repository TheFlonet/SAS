package businesslogic.task;

import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;

public class Task {
    private final KitchenProcess process;
    private int time;
    private int quantity;
    private User cook;

    public Task(KitchenProcess kitchenProcess) {
        process = kitchenProcess;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getCook() {
        return cook;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }
}
