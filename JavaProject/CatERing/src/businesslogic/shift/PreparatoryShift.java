package businesslogic.shift;

import java.time.LocalDate;
import java.time.LocalTime;

public class PreparatoryShift extends Shift {

    public PreparatoryShift(LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(date, startTime, endTime);
        this.type = Type.PREPARATORY;
    }

    @Override
    public Type getType() { return this.type; }
}
