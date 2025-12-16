package synapt.flitsfiets.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import synapt.flitsfiets.common.enums.SubscriptionType;

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
    private SubscriptionType subscription;

    @Column(nullable = false)
    private Instant startDate;
    private Instant endDate;

//    Add blamable?
}
