package synapt.flitsfiets.mailing.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.mailing.model.UserTrack;
import synapt.flitsfiets.mailing.repository.UserTrackRepository;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;

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

    public UserExtendedDTO getByEmail(String email){
        return modelMapper.map(userTrackRepository.findByEmail(email), UserExtendedDTO.class);

    }
}
