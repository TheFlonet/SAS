import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.EventInfo;
import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenProcess;
import businesslogic.shift.Shift;
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

            System.out.println("\n TEST CREATE MENU");
            Menu m = CatERing.getInstance().getMenuManager().createMenu("Menu di Test");
            System.out.println(m.toString());

            System.out.println("TEST CREATE SHEET");
            /* TODO messo solo per evidenziare la nota:
             *  il recupero degli eventi corrisponderebbe a una operazione di sistema del tipo "consultaListaEventi",
             *  da mettere a partire da UC dettagliato; quello che propongo è di dire che, avendo fatto le documentazioni
             *  in gruppo a laboratorio, ci siamo accorti solo ora di questa mancanza e quindi simuliamo questa operazione
             *  assumendo che si abbia già la lista di eventi"
             */
            List<EventInfo> events = CatERing.getInstance().getEventManager().getEventInfo();

            /* TODO messo solo per evidenziare la nota:
             *  questa operazione indica errore perché il tipo di ritorno delle get() è ServiceInfo, classe usata nell'ultima
             *  versione del loro progetto; per non creare conflitti ho inserito ServiceInfo nel package, direi di usare quella
             *  classe per partire e aggiungere le nostre cose
             */
            SummarySheet newSheet = CatERing.getInstance().getTaskManager().createSummarySheet(events.get(0).getServices().get(0));
            // TODO: SummarySheet.toString()
            System.out.println(newSheet);

            System.out.println("TEST ADD TASK");
            /* TODO:
             *  - mettere ordine e coerenza, convertendo tutte le terminologie in KitchenProcess, non più Recipe
             *  - fare il "merge" tra le nostre classi (KitchenProcess) e quelle di laboratorio (Recipe)
             */
            KitchenProcess[] recipeBook = CatERing.getInstance().getRecipeManager().getKitchenProcesses();
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook[2]);
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook[6]);
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook[1]);
            CatERing.getInstance().getTaskManager().addTaskToSheet(recipeBook[0]);
            System.out.println(newSheet);

            System.out.println("TEST MOVE TASK");
            CatERing.getInstance().getTaskManager().moveTask(newSheet.getTasks().get(0), 2);
            System.out.println(newSheet);

            System.out.println("TEST GET SHIFT BOARD");
            ShiftBoard board = CatERing.getInstance().getShiftManager().getShiftBoard();
            // TODO: ShiftBoard.toString()
            System.out.println(board);

            System.out.println("TEST ASSIGN SHIFT");
            // TODO: List<Shift> shifts = CatERing.getInstance().getShiftManager().getShifts() Modo per recuperare i turni??
            // TODO: implementazione di Shift (ti direi PreparatoryShift, ma solo lo strettissimo necessario)
            // TODO: modo per recuperare i cuochi ?? (poi facciamo noi bene le aggiunte affinché i cuochi siano quelli giusti per i turni )
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shift1, cuoco1, 20, 2);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(1), shift1, cuoco2, 10, 3);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(3), shift2, 20, 2);
            CatERing.getInstance().getTaskManager().assignTask(newSheet.getTasks().get(0), shift3);


            /* TODO generale: cose da rendere persistenti
             *  - fogli_riepilogativi(id AUTOINCR, id_servizio, creatore, *)
             *  - compiti(id AUTOINCR, id_foglio, id_ricetta, id_utente?, *)
             *  - turni_preparatori(id AUTOINCR, data, orainizio, orafine, *)
             *  - associazione_turno_compito(id_turno, id_compito, *)
             *  - associazione_turno_cuoco(id_turno, id_utente, *)
             */

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
