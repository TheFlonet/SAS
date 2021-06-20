package businesslogic.task;

import businesslogic.recipe.KitchenProcess;
import businesslogic.user.User;

public class Task {
    // TODO: mappa statica con i compiti già caricati dal DB (come fatto ad es. con gli utenti)
    private final KitchenProcess process;
    private long time;
    private int quantity;
    private User cook;

    public Task(KitchenProcess kitchenProcess) {
        process = kitchenProcess;
    }

    @Override
    public String toString() {
        return "Task{" +
                "process=" + process +
                ", time=" + time +
                ", quantity=" + quantity +
                ", cook=" + cook +
                '}';
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
