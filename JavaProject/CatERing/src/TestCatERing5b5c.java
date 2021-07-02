import businesslogic.CatERing;
import businesslogic.UnavailableCookException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.Shift;
import businesslogic.shift.ShiftBoard;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Random;

public class TestCatERing5b5c {
    public static void main(String[] args) {
        try {
            System.out.println("\nTEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Tony");
            User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
            System.out.println(currentUser);

            System.out.println("\nTEST SET SHEET");
            SummarySheet existingSheet = SummarySheet.loadExistingSheet();
            CatERing.getInstance().getTaskManager().setCurrentSheet(existingSheet);
            System.out.println(existingSheet);

            System.out.println("\nTEST ADD TASK");
            List<KitchenProcess> kitchenProcessesBook = CatERing.getInstance().getKitchenProcessManager().getKitchenProcesses();
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(16));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(13));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(11));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(18));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(8));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(10));
            System.out.println(existingSheet);

            System.out.println("\nTEST BOARD");
            ShiftBoard board = CatERing.getInstance().getTaskManager().getShiftBoard();
            System.out.println(board);

            System.out.println("\nTEST ASSIGN SHIFT");
            ObservableList<Shift> shifts = Shift.getAllShifts();
            ObservableList<User> cooks = User.getAllCooks();

            try {
                CatERing.getInstance().getTaskManager().assignTask(existingSheet.getTasks().get(0), shifts.get(0), cooks.get(0), 20, 2);
                CatERing.getInstance().getTaskManager().assignTask(existingSheet.getTasks().get(1), shifts.get(0), cooks.get(1), 10, 3);
                CatERing.getInstance().getTaskManager().assignTask(existingSheet.getTasks().get(3), shifts.get(1), 20, 2);
                CatERing.getInstance().getTaskManager().assignTask(existingSheet.getTasks().get(2), shifts.get(2));
            } catch (UnavailableCookException e) {
                e.printStackTrace();
                System.out.println("Questa cosa non dovrebbe stampare");
            }

            try {
                CatERing.getInstance().getTaskManager().assignTask(existingSheet.getTasks().get(4), shifts.get(3), cooks.get(0));
            } catch (UnavailableCookException e) {
                System.out.println("Il cuoco non è assegnato al turno");
            }

            System.out.println(board);

            System.out.println("\nTEST REMOVE ASSIGNMENT");
            CatERing.getInstance().getTaskManager().removeAssignment(new Task(kitchenProcessesBook.get(11)));
            CatERing.getInstance().getTaskManager().removeAssignment(new Task(kitchenProcessesBook.get(16)));
            System.out.println(board);

            try {
                CatERing.getInstance().getTaskManager().removeAssignment(new Task(kitchenProcessesBook.get(10)));
            } catch (UseCaseLogicException e) {
                System.out.println(
                    "Il compito di cui voglio rimuovere assegnamento al turno" +
                    " non era assegnato ad alcun turno");
            }

            System.out.println("\nTEST ADD SPECIFICATIONS");
            Random r = new Random();
            int[] tasks_id = {28,30,31};
            for(int i : tasks_id) {
                Task t = Task.getLoadedTaskById(i);
                CatERing.getInstance().getTaskManager().addSpecification(t, r.nextInt(100), r.nextInt(20));
            }

            try {
                Task t = Task.getLoadedTaskById(28);
                Shift s = shifts.get(3);
                CatERing.getInstance().getTaskManager().assignTask(t, s);
                System.out.println("Ora il seguente turno contiene il Task t: " + s);
                CatERing.getInstance().getTaskManager().addSpecification(t, cooks.get(1));
                System.out.println("Ora ho segnato che il Task t viene svolto da " + cooks.get(1));
            } catch (UseCaseLogicException | UnavailableCookException e) {
                System.out.println("Non dovrei leggere questo");
            }

            try {
                Task t = Task.getLoadedTaskById(30);
                Shift s = shifts.get(2);
                CatERing.getInstance().getTaskManager().assignTask(t, s);
                System.out.println("Ora il seguente turno contiene il Task t: " + s);
                CatERing.getInstance().getTaskManager().addSpecification(t, cooks.get(2));
            } catch (UseCaseLogicException | UnavailableCookException e) {
                System.out.println("Il cuoco che voglio svolga il compito non lavora nel turno in cui il compito è svolto");
            }

        } catch (UseCaseLogicException e) {
            System.out.println(e.getMessage());
            System.out.println("Errore di logica nello use case");
        }
    }
}