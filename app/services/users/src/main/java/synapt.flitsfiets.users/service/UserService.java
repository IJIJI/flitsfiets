package synapt.flitsfiets.users.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.users.model.User;
import synapt.flitsfiets.users.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserExtendedDTO createUser(UserFullPasswordDTO user){
        User newUser = modelMapper.map(user, User.class);

        User createdUser = userRepository.save(newUser);
        userRepository.flush();

        return modelMapper.map(createdUser, UserExtendedDTO.class);
    }

}
