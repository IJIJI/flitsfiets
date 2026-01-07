import FullCalendar from "@fullcalendar/react";
import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';




export default function SlotPicker({slots}:
{slots:
        {
            start: string,
            end: string,
            title?: string,
            backgroundColor?: string
        }[] | null
}) {

    if (slots == null)
        slots = [];

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
                events={slots}
                //TODO add valid range?
                initialDate={slots[0].start}
            />
        </div>
    )
}