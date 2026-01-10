package synapt.flitsfiets.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synapt.flitsfiets.users.model.User;
import synapt.flitsfiets.users.model.UserSubscriptionPeriod;

import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionPeriod, Long> {

}
