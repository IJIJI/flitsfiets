package synapt.flitsfiets.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synapt.flitsfiets.appointments.model.UserTrack;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    UserTrack findByEmail(String email);
    UserTrack findById(long id);

    boolean existsByEmail(String email);
}
