package businesslogic.event;

import businesslogic.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;

import java.sql.Date;

public class Event {
    private int id;
    private final String name;
    private Date dateStart;
    private Date dateEnd;
    private int participants;
    private User organizer;

    private ObservableList<Service> services;

    public Event(String name) {
        this.name = name;
        id = 0;
    }

    public ObservableList<Service> getServices() {
        return FXCollections.unmodifiableObservableList(this.services);
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<Event> loadAllEvent() {
        ObservableList<Event> all = FXCollections.observableArrayList();
        String query = "SELECT * FROM Events WHERE true";
        PersistenceManager.executeQuery(query, rs -> {
            String n = rs.getString("name");
            Event e = new Event(n);
            e.id = rs.getInt("id");
            e.dateStart = rs.getDate("date_start");
            e.dateEnd = rs.getDate("date_end");
            e.participants = rs.getInt("expected_participants");
            int org = rs.getInt("organizer_id");
            e.organizer = User.loadUserById(org);
            all.add(e);
        });

        for (Event e : all) {
            e.services = Service.loadServiceInfoForEvent(e.id);
        }
        return all;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", participants=" + participants +
                ", organizer=" + organizer +
                ", services=" + services +
                '}';
    }
}
