package businesslogic.task;

import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;

public class Task {
    private final KitchenProcess process;
    private long time;
    private int quantity;
    private User cook;

    public Task(KitchenProcess kitchenProcess) {
        process = kitchenProcess;
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
}
