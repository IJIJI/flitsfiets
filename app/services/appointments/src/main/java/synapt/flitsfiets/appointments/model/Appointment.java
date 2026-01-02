package synapt.flitsfiets.appointments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import synapt.flitsfiets.appointments.enums.AppointmentType;
import synapt.flitsfiets.common.enums.Location;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant start;

    private Long userId; //VO?

    private AppointmentType type;

    private Location location; // Shouldn't be enum
}
