package synapt.flitsfiets.appointments.util;

import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.dto.appointment.TimeSlotDTO;
import synapt.flitsfiets.common.enums.Location;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
/*
 * Yes this is not how it should be done. A lot of cases will not work and a lot is not taken account of.
 * I am not currently implementing actual appointment availability, this is just a placeholder.
 */
public class RosterUtil
{
    private final LocalTime dayStart = LocalTime.of(8, 0);
    private final LocalTime dayEnd = LocalTime.of(18, 0);
    private final Integer slotMinutes = 15;
    private final ZoneId zoneId = ZoneId.of("Europe/Amsterdam");
    private final Integer workDaysInFuture = 10;

    public List<TimeSlotDTO> generatePlaceholderRoster(Location location)
    {
        //        ZonedDateTime nextFirst = ZonedDateTime.now(Time.of);
        LocalDate curDate = LocalDate.now(zoneId);
        List<TimeSlotDTO> output = new ArrayList<>();

        for (int x = 0; x < workDaysInFuture; x++)
        {
            LocalTime curDayStart = dayStart;

            if (curDate.getDayOfWeek() == DayOfWeek.SATURDAY)
                curDate = curDate.plusDays(1);
            if (curDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                curDate = curDate.plusDays(1);

            if (x == 0)
                curDayStart = LocalTime.now(zoneId);

            output.addAll(
                    this.generateDayRoster(
                            location,
                            curDayStart, curDate)
            );

            curDate = curDate.plusDays(1);
        }

        int index = 0;
        for(TimeSlotDTO slot : output){
            slot.setId(index++);
        }

        return output;
    }

    public List<TimeSlotDTO> generateDayRoster(Location location, LocalTime startTime, LocalDate day)
    {
        Random random = new Random();

        List<TimeSlotDTO> output = new ArrayList<>();

        LocalTime iterateTime = startTime;

        if ((startTime.getMinute() + startTime.getHour() * 60) % slotMinutes != 0)
            iterateTime = startTime.plusMinutes(slotMinutes - (startTime.getMinute() + startTime.getHour() * 60) % slotMinutes);

        while (localTimeIsBeforeOrEqual(iterateTime.plusMinutes(slotMinutes), dayEnd))
        {
            ZonedDateTime zoneTime = ZonedDateTime.of(day, iterateTime, zoneId);

            TimeSlotDTO slot = new TimeSlotDTO();

            slot.setStart(zoneTime.toInstant());
            slot.setEnd(zoneTime.plusMinutes(slotMinutes).toInstant());
            slot.setLocation(location);
            slot.setSpots(3);
            slot.setSpotsFilled(random.nextInt(3 + 1));

            output.add(
                    slot
            );

            iterateTime = iterateTime.plusMinutes(slotMinutes);
        }

        return output;
    }

    public int getSlotLength(){
        return slotMinutes;
    }

    public boolean localTimeIsBeforeOrEqual(LocalTime date, LocalTime compare)
    {
        return date.isBefore(compare) || date.equals(compare);
    }
}
