package synapt.flitsfiets.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import synapt.flitsfiets.common.enums.UserType;
import synapt.flitsfiets.common.valueObject.UserAddress;
import synapt.flitsfiets.users.converter.UserAddressAttributeConverter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

//    Should be VO stored in the common library
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @Column(unique=true, nullable = false)
    private String email;
    private boolean emailVerified = false;

    private String telephone;

    @Column(nullable = false)
    private String password;

    @Convert(converter = UserAddressAttributeConverter.class)
    @Column(columnDefinition = "CLOB")
    private UserAddress address;

//    Should be role based. No security implemented. For now, just a simple enum.
    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<UserSubscriptionPeriod> subscriptions;

    @CreatedDate
    private Instant created;
    @LastModifiedDate
    private Instant updated;
//    Add blamable?

    public User(String email, String telephone, String password, String name, String surname) {
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public UserSubscriptionPeriod getActiveSubscription() {
        if (subscriptions == null)
            return null;

        Instant now = Instant.now();
        for (UserSubscriptionPeriod subscription : subscriptions) {
            if (subscription.getStartDate().isBefore(now) && subscription.getEndDate().isAfter(now))
                return subscription;
        }

        return null;
    }
}
