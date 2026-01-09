import FullCalendar from "@fullcalendar/react";
import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';
import '../calendar.less';
import {createRef, useState} from "react";


const testSlots = JSON.parse(`[

  {
    "id": 0,
    "start": "2026-01-09T16:15:47.756095Z",
    "end": "2026-01-09T16:30:47.756095Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 1,
    "start": "2026-01-09T16:30:47.756095Z",
    "end": "2026-01-09T16:45:47.756095Z",
    "title": "1 spots left",
    "backgroundColor": "#F59B1D"
  },
  {
    "id": 2,
    "start": "2026-01-12T07:00:00Z",
    "end": "2026-01-12T07:15:00Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 3,
    "start": "2026-01-12T07:15:00Z",
    "end": "2026-01-12T07:30:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 4,
    "start": "2026-01-12T07:30:00Z",
    "end": "2026-01-12T07:45:00Z",
    "title": "1 spots left",
    "backgroundColor": "#F59B1D"
  },
  {
    "id": 5,
    "start": "2026-01-12T07:45:00Z",
    "end": "2026-01-12T08:00:00Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 6,
    "start": "2026-01-12T08:00:00Z",
    "end": "2026-01-12T08:15:00Z",
    "title": "1 spots left",
    "backgroundColor": "#F59B1D"
  },
  {
    "id": 7,
    "start": "2026-01-12T08:15:00Z",
    "end": "2026-01-12T08:30:00Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 8,
    "start": "2026-01-12T08:30:00Z",
    "end": "2026-01-12T08:45:00Z",
    "title": "1 spots left",
    "backgroundColor": "#F59B1D"
  },
  {
    "id": 9,
    "start": "2026-01-12T08:45:00Z",
    "end": "2026-01-12T09:00:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 10,
    "start": "2026-01-12T09:00:00Z",
    "end": "2026-01-12T09:15:00Z",
    "title": "1 spots left",
    "backgroundColor": "#F59B1D"
  },
  {
    "id": 11,
    "start": "2026-01-12T09:15:00Z",
    "end": "2026-01-12T09:30:00Z",
    "title": "3 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 12,
    "start": "2026-01-12T09:30:00Z",
    "end": "2026-01-12T09:45:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 13,
    "start": "2026-01-12T09:45:00Z",
    "end": "2026-01-12T10:00:00Z",
    "title": "3 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 14,
    "start": "2026-01-12T10:00:00Z",
    "end": "2026-01-12T10:15:00Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 15,
    "start": "2026-01-12T10:15:00Z",
    "end": "2026-01-12T10:30:00Z",
    "title": "2 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 16,
    "start": "2026-01-12T10:30:00Z",
    "end": "2026-01-12T10:45:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 17,
    "start": "2026-01-12T10:45:00Z",
    "end": "2026-01-12T11:00:00Z",
    "title": "3 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 18,
    "start": "2026-01-12T11:00:00Z",
    "end": "2026-01-12T11:15:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 19,
    "start": "2026-01-12T11:15:00Z",
    "end": "2026-01-12T11:30:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 20,
    "start": "2026-01-12T11:30:00Z",
    "end": "2026-01-12T11:45:00Z",
    "title": "0 spots left",
    "backgroundColor": "#C70000"
  },
  {
    "id": 21,
    "start": "2026-01-12T11:45:00Z",
    "end": "2026-01-12T12:00:00Z",
    "title": "3 spots left",
    "backgroundColor": "#00BA28"
  },
  {
    "id": 22,
    "start": "2026-01-12T12:00:00Z",
    "end": "2026-01-12T12:15:00Z",
    "title": "3 spots left",
    "backgroundColor": "#00BA28"
  } 
]`);

interface ActiveSlot {
    id?: string;
    start?: Date | null;
    end?: Date | null;
    full?: boolean | null;
}


export default function SlotPicker({slots}:
                                   {
                                       slots:
                                           {
                                               start: string,
                                               end: string,
                                               title?: string,
                                               backgroundColor?: string
                                           }[] | null
                                   }) {

    const calendarRef = createRef<FullCalendar>()
    const [selectedSlot, setSelectedSlot] = useState<ActiveSlot | null>(null);


    if (slots == null)
        slots = testSlots;
    // slots = [];

    const parseSelectedSlot = (id: string) => {
        // const slotsWithActiveClass = document.getElementsByClassName("activeEvent");
        //
        // for(const slot of slotsWithActiveClass){
        //     slot.classList.remove("activeEvent");
        // }

        if (selectedSlot?.id != null) {
            const oldSelected = calendarRef.current?.getApi().getEventById(selectedSlot.id);
            oldSelected?.setProp('classNames', '');
        }

        const newSelected = calendarRef.current?.getApi().getEventById(id);
        newSelected?.setProp('classNames', 'activeEvent');

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

                events={slots || undefined}
                //TODO add valid range?
                initialDate={slots == null ? undefined : slots[0].start}
            />
        </div>
    )
}