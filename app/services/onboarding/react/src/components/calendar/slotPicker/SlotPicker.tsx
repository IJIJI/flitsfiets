import FullCalendar from "@fullcalendar/react";
import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';
import '../calendar.less';
import {createRef, useState} from "react";


const testSlots = JSON.parse(`[
  {
    "id": 0,
    "start": "2026-01-12T07:00:00Z",
    "end": "2026-01-12T07:15:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 2
  },
  {
    "id": 1,
    "start": "2026-01-12T07:15:00Z",
    "end": "2026-01-12T07:30:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 2,
    "start": "2026-01-12T07:30:00Z",
    "end": "2026-01-12T07:45:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 3
  },
  {
    "id": 3,
    "start": "2026-01-12T07:45:00Z",
    "end": "2026-01-12T08:00:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 4,
    "start": "2026-01-12T08:00:00Z",
    "end": "2026-01-12T08:15:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 5,
    "start": "2026-01-12T08:15:00Z",
    "end": "2026-01-12T08:30:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 0
  },
  {
    "id": 6,
    "start": "2026-01-12T08:30:00Z",
    "end": "2026-01-12T08:45:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 7,
    "start": "2026-01-12T08:45:00Z",
    "end": "2026-01-12T09:00:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 2
  },
  {
    "id": 8,
    "start": "2026-01-12T09:00:00Z",
    "end": "2026-01-12T09:15:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 9,
    "start": "2026-01-12T09:15:00Z",
    "end": "2026-01-12T09:30:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 10,
    "start": "2026-01-12T09:30:00Z",
    "end": "2026-01-12T09:45:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 2
  },
  {
    "id": 11,
    "start": "2026-01-12T09:45:00Z",
    "end": "2026-01-12T10:00:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 0
  },
  {
    "id": 12,
    "start": "2026-01-12T10:00:00Z",
    "end": "2026-01-12T10:15:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 0
  },
  {
    "id": 13,
    "start": "2026-01-12T10:15:00Z",
    "end": "2026-01-12T10:30:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 1
  },
  {
    "id": 14,
    "start": "2026-01-12T10:30:00Z",
    "end": "2026-01-12T10:45:00Z",
    "location": "DELFT",
    "spots": 3,
    "spotsFilled": 0
  }
]`);

interface CalendarSlot {
    id: string;
    start: Date | null;
    end: Date | null;
    location?: string;
    spots: number;
    spotsFilled: number;
    title?: string;
    backgroundColor?: string
    full?: boolean | null;
}

interface ActiveSlot {
    id?: string;
    start?: Date | null;
    end?: Date | null;
    full?: boolean | null;
}


export default function SlotPicker({slots}: { slots: CalendarSlot[] | null }) {

    const calendarRef = createRef<FullCalendar>()
    const [selectedSlot, setSelectedSlot] = useState<ActiveSlot | null>(null);

    if (slots == null)
        slots = testSlots;
    // slots = [];
    if (slots != null) {
        slots.forEach(function (slot, index) {

            if (slot.spots == slot.spotsFilled) {
                slots[index].backgroundColor = "#C70000";
                slots[index].title = "Full";
                slots[index].full = true;
            } else if (slot.spotsFilled / slot.spots > 0.6) {
                slots[index].backgroundColor = "#F59B1D";
                slots[index].title = "Only " + (slot.spots - slot.spotsFilled) + " spots left!";
            } else {
                slots[index].backgroundColor = "#00BA28";
                slots[index].title = (slot.spots - slot.spotsFilled) + " spots left";
            }

        }); // use arr as this
    }

    const parseSelectedSlot = (id: string) => {

        if (selectedSlot?.id != null) {
            const oldSelected = calendarRef.current?.getApi().getEventById(selectedSlot.id);
            oldSelected?.setProp('classNames', '');
        }

        const newSelected = calendarRef.current?.getApi().getEventById(id);

        if (newSelected?.extendedProps.full)
            newSelected?.setProp('classNames', 'activeEvent full');
        else
            newSelected?.setProp('classNames', 'activeEvent');

        console.log(newSelected);

        setSelectedSlot({
            id: newSelected?.id,
            start: newSelected?.start,
            end: newSelected?.end
        });
    }

    return (
        <div className="calendar">
            <FullCalendar
                ref={calendarRef}
                plugins={[listPlugin, timeGridPlugin]}
                initialView="listDay"
                headerToolbar={{
                    left: 'title',
                    right: 'prev,next',
                }}
                // aspectRatio={isPhone ? 0.8 : isMobile ? 1.25 : 1.9}
                aspectRatio={1.25}

                weekNumbers={true}
                nowIndicator={true}
                locale={"NL"}
                firstDay={1}
                businessHours={{
                    daysOfWeek: [1, 2, 3, 4, 5], // Monday - Friday
                    startTime: '08:00',
                    endTime: '18:00',
                }}

                eventClick={(info) => {
                    parseSelectedSlot(info.event.id);
                }}

                events={(slots as object) || undefined}
                //TODO add valid range?
                initialDate={slots == null ? undefined : slots[0].start as Date}
            />
        </div>
    )
}