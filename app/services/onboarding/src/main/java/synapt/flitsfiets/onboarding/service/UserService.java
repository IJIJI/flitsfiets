package synapt.flitsfiets.onboarding.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.dto.user.UserFullDTO;

import java.nio.charset.StandardCharsets;

@Service
public class UserService
{
    @Autowired
    private RestClient usersRestClient;

    public UserExtendedDTO onBoardUser(UserFullDTO newUser)
    {
        return usersRestClient
                .post()
                .uri("/users")
                .body(newUser)
                .retrieve()
                .onStatus(
                        status -> status.value() == 409,
                        (req, res) -> {
                            String body = StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8);

                            throw new DuplicateKeyException(body);
                        }
                )
                .onStatus(
                        status -> status.value() != 200 && status.value() != 409,
                        (req, res) -> {
                            String body = StreamUtils.copyToString(res.getBody(), StandardCharsets.UTF_8);

                            throw new BadRequestException(body);
                        }
                )
                .body(UserExtendedDTO.class);
    }
}
