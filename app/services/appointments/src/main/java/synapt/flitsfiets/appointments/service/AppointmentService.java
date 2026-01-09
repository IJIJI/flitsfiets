package synapt.flitsfiets.appointments.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.appointments.model.Appointment;
import synapt.flitsfiets.appointments.repository.AppointmentRepository;
import synapt.flitsfiets.common.enums.Location;

import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService
{
    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Location> getLocations()
    {
        return Arrays.asList(Location.values());
    }

    public Appointment getAppointment(Long id)
    {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment does not exist"));
    }

    List<Appointment> getUserAppointments(Long userId){
        return appointmentRepository.findAllByUserId(userId);
    }

    public Appointment createAppointment(Appointment appointment){

        Appointment newAppointment = appointmentRepository.save(appointment);
        appointmentRepository.flush();

        return newAppointment;
    }
}
