package synapt.flitsfiets.appointments.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.appointments.model.UserTrack;
import synapt.flitsfiets.appointments.repository.UserTrackRepository;

@Service
public class UserTrackService
{
    @Autowired
    UserTrackRepository userTrackRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserExtendedDTO addUser(UserTrack newUser){
        UserTrack savedUser = userTrackRepository.save(newUser);
        userTrackRepository.flush();
        return modelMapper.map(savedUser, UserExtendedDTO.class);
    }

    public boolean emailExists(String email)
    {
        return userTrackRepository.existsByEmail(email);
    }

    public UserTrack getByEmail(String email){
        return userTrackRepository.findByEmail(email);

    }

    public UserTrack getById(long id){
        return userTrackRepository.findById(id);
    }
}
