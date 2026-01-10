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
import synapt.flitsfiets.users.model.UserSubscriptionPeriod;
import synapt.flitsfiets.users.repository.UserRepository;
import synapt.flitsfiets.users.repository.UserSubscriptionRepository;

import java.time.Instant;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSubscriptionRepository userSubscriptionRepository;
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

        UserSubscriptionPeriod subscriptionPeriod = new UserSubscriptionPeriod();
        subscriptionPeriod.setStartDate(Instant.now());
        subscriptionPeriod.setBike(user.getActiveBikeType());
        subscriptionPeriod.setPlan(user.getActivePlanType());

        User createdUser = userRepository.save(newUser);
        userRepository.flush();

        subscriptionPeriod.setUser(createdUser);
        userSubscriptionRepository.save(subscriptionPeriod);
        userSubscriptionRepository.flush();


        UserExtendedDTO createdDTO = modelMapper.map(createdUser, UserExtendedDTO.class);

        createdDTO.setActivePlanType(subscriptionPeriod.getPlan());
        createdDTO.setActiveBikeType(subscriptionPeriod.getBike());

        usersEventPublisher.publishUserCreated(new UserCreated(createdDTO));

        return createdDTO;
    }

    public boolean emailExists(String email)
    {
        return userRepository.existsByEmail(email);
    }
}
