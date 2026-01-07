import FullCalendar from "@fullcalendar/react";
import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';


const testSlots = [
    {
        start: "2026-01-08T07:00:00Z",
        end: "2026-01-08T07:15:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T07:15:00Z",
        end: "2026-01-08T07:30:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T07:30:00Z",
        end: "2026-01-08T07:45:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T07:45:00Z",
        end: "2026-01-08T08:00:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T08:00:00Z",
        end: "2026-01-08T08:15:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T08:15:00Z",
        end: "2026-01-08T08:30:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T08:30:00Z",
        end: "2026-01-08T08:45:00Z",
        location: "DELFT"
    },
    {
        start: "2026-01-08T08:45:00Z",
        end: "2026-01-08T09:00:00Z",
        location: "DELFT"
    },
];


export default function SlotPicker({slots}: {slots: object|null}) {

    return (
        <div className="calendar">
            <FullCalendar
                plugins={[ listPlugin, timeGridPlugin ]}
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
                // events={slots}
                events={testSlots}
            />
        </div>
    )
}