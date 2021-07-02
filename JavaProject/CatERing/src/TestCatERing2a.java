import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.recipe.KitchenProcess;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.user.User;

import java.util.List;

public class TestCatERing2a {
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
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(2));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(1));
            CatERing.getInstance().getTaskManager().addTaskToSheet(kitchenProcessesBook.get(0));
            System.out.println(existingSheet);

            System.out.println("\nTEST REMOVE TASKS FROM SHEET");
            Task[] tasks = {
                new Task(kitchenProcessesBook.get(0)),
                new Task(kitchenProcessesBook.get(1)),
                new Task(kitchenProcessesBook.get(2))};
            for(Task t : tasks) {
                CatERing.getInstance().getTaskManager().removeTaskFromSheet(t);
            }

            System.out.println(existingSheet);

        } catch (UseCaseLogicException e) {e.printStackTrace();}
    }
}
