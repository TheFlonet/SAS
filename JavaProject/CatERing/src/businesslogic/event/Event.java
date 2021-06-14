package businesslogic.event;

import java.util.List;

public class Event {
    private final List<Service> services;
    public String information;

    public Event(List<Service> services) {
        this.services = services;
    }
}
