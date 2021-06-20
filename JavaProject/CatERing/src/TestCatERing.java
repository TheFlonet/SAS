import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.ShiftBoard;
import businesslogic.task.SummarySheet;
import businesslogic.user.User;

import java.util.List;

public class TestCatERing {
    public static void main(String[] args) {
        try {
            /* System.out.println("TEST DATABASE CONNECTION");
            PersistenceManager.testSQLConnection();*/
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Marinella");
            User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
            System.out.println(currentUser);

            System.out.println("TEST CREATE SHEET");
            List<Event> events = CatERing.getInstance().getEventManager().getEvents();

            SummarySheet newSheet = CatERing.getInstance().getTaskManager().createSummarySheet(events.get(0).getServices().get(0));
            System.out.println(newSheet);

            System.out.println("TEST ADD TASK");
            List<KitchenProcess> recipeBook = CatERing.getInstance().getKitchenProcessManager().getKitchenProcesses();
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook.get(2));
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook.get(6));
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook.get(1));
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook.get(0));
            System.out.println(newSheet);

            System.out.println("TEST MOVE TASK");
            CatERing.getInstance().getTaskManager().moveTask(newSheet.getTasks().get(0), 2);
            System.out.println(newSheet);

            System.out.println("TEST GET SHIFT BOARD");
            ShiftBoard board = CatERing.getInstance().getShiftManager().getShiftBoard();
            System.out.println(board);

            System.out.println("TEST ASSIGN SHIFT");
            // TODO: List<Shift> shifts = CatERing.getInstance().getShiftManager().getShifts() Modo per recuperare i turni??
            // TODO: modo per recuperare i cuochi ?? (poi facciamo noi bene le aggiunte affinché i cuochi siano quelli giusti per i turni )
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shift1, cuoco1, 20, 2);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(1), shift1, cuoco2, 10, 3);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(3), shift2, 20, 2);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shift3);
            System.out.println(board);

            /* TODO generale: cose da rendere persistenti
             *  - associazione_turno_cuoco(id_turno, id_utente, *)
             */

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
