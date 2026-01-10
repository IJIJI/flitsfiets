package synapt.flitsfiets.appointments.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.appointments.messaging.appointments.AppointmentEventPublisher;
import synapt.flitsfiets.appointments.model.Appointment;
import synapt.flitsfiets.appointments.repository.AppointmentRepository;
import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;
import synapt.flitsfiets.common.enums.Location;
import synapt.flitsfiets.common.events.appointments.AppointmentCreated;

import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService
{
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentEventPublisher appointmentEventPublisher;
    @Autowired
    private ModelMapper modelMapper;

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

    public AppointmentDTO createAppointment(Appointment appointment){

        AppointmentDTO newAppointment = modelMapper.map(appointmentRepository.save(appointment), AppointmentDTO.class);
        appointmentRepository.flush();

        appointmentEventPublisher.publishAppointmentCreated(new AppointmentCreated(newAppointment));

        return newAppointment;
    }
}
