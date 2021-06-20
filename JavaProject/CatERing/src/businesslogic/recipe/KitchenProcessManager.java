package businesslogic.recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class KitchenProcessManager {
    public KitchenProcessManager() {
        KitchenProcess.loadAllKitchenProcesses();
    }

    public ObservableList<KitchenProcess> getKitchenProcesses() {
        return FXCollections.unmodifiableObservableList(KitchenProcess.getAllKitchenProcesses());
    }
}
