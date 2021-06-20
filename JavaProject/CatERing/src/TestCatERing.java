import businesslogic.CatERing;
import businesslogic.UnavailableCookException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.Shift;
import businesslogic.shift.ShiftBoard;
import businesslogic.task.SummarySheet;
import businesslogic.user.User;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;

import javax.annotation.processing.SupportedOptions;
import java.util.List;

public class TestCatERing {
    public static void main(String[] args) {
        try {
            /*System.out.println("TEST DATABASE CONNECTION");
            PersistenceManager.testSQLConnection();*/
            System.out.println("\nTEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Marinella");
            User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
            System.out.println(currentUser);

            System.out.println("\nTEST CREATE SHEET");
            List<Event> events = CatERing.getInstance().getEventManager().getEvents();
            SummarySheet newSheet = CatERing.getInstance().getTaskManager().createSummarySheet(events.get(0).getServices().get(0));
            System.out.println(newSheet);

            System.out.println("\nTEST ADD TASK");
            List<KitchenProcess> kitchenProcessesBook = CatERing.getInstance().getKitchenProcessManager().getKitchenProcesses();
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(6));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(16));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(10));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(8));
            // questi sono già presenti e non dovrebbero essere aggiunti
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(2));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(1));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(0));
            System.out.println(newSheet);

            System.out.println("\nTEST MOVE TASK");
            CatERing.getInstance().getTaskManager().moveTask(newSheet.getTasks().get(0), 2);
            System.out.println(newSheet);

            System.out.println("\nTEST GET SHIFT BOARD");
            ShiftBoard board = CatERing.getInstance().getShiftManager().getShiftBoard();
            System.out.println(board);

            System.out.println("\nTEST ASSIGN SHIFT");
            ObservableList<Shift> shifts = Shift.getAllShifts();
            ObservableList<User> cooks = User.getAllCooks();

            try {
                CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shifts.get(0), cooks.get(0), 20, 2);
                CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(1), shifts.get(0), cooks.get(1), 10, 3);
                CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(3), shifts.get(1), 20, 2);
                CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shifts.get(2));
            } catch (UnavailableCookException e) {
                e.printStackTrace();
                System.out.println("Questa cosa non dovrebbe stampare");
            }

            System.out.println(board);

        } catch (UseCaseLogicException e) {
            System.out.println(e.getMessage());
            System.out.println("Errore di logica nello use case");
        }
    }
}
