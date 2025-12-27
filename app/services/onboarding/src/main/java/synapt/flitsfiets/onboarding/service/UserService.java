package synapt.flitsfiets.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullDTO;

@Service
public class UserService
{
    @Autowired
    private RestClient usersRestClient;

    public UserExtendedDTO onBoardUser(UserFullDTO newUser){
        return usersRestClient.post().uri("/users").body(newUser).retrieve().body(UserExtendedDTO.class);
    }
}
