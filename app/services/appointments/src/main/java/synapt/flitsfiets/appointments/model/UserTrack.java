package synapt.flitsfiets.appointments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

@Entity
@Table(name = "user_track")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserTrack {

//    Should be VO stored in the common library
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(unique=true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private BikeType bikeType;
    @Enumerated(EnumType.STRING)
    private PlanType planType;
}
