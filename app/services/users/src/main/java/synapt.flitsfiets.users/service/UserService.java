package synapt.flitsfiets.users.service;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullPasswordDTO;
import synapt.flitsfiets.common.events.users.UserCreated;
import synapt.flitsfiets.users.messaging.UsersEventPublisher;
import synapt.flitsfiets.users.model.User;
import synapt.flitsfiets.users.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private final UsersEventPublisher usersEventPublisher;

    public UserService(UsersEventPublisher usersEventPublisher)
    {
        this.usersEventPublisher = usersEventPublisher;
    }

    @Transactional
    public UserExtendedDTO createUser(UserFullPasswordDTO user){
        User newUser = modelMapper.map(user, User.class);

        User createdUser = userRepository.save(newUser);
        userRepository.flush();

        UserExtendedDTO createdDTO = modelMapper.map(createdUser, UserExtendedDTO.class);

        usersEventPublisher.publishUserCreated(new UserCreated(createdDTO));

        return createdDTO;
    }

}
