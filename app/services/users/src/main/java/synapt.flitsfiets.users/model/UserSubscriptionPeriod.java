package synapt.flitsfiets.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionPeriod {

    //    Should be VO stored in the common library
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //    Should be VO stored in the common library
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //    Should be VO stored in the common library
    @Enumerated(EnumType.STRING)
    private BikeType bike;


    //    Should be VO stored in the common library
    @Enumerated(EnumType.STRING)
    private PlanType plan;

    @Column(nullable = false)
    private Instant startDate;
    private Instant endDate;

//    Add blamable?
}
