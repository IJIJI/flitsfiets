package synapt.flitsfiets.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synapt.flitsfiets.appointments.model.Appointment;
import synapt.flitsfiets.common.enums.Location;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
    List<Appointment> findAllByUserId(Long userId);
    Appointment findByLocation(Location location);

}
