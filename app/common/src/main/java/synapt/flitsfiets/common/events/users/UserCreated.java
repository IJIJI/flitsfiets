package synapt.flitsfiets.common.events.users;

import synapt.flitsfiets.common.dto.user.UserExtendedDTO;

public record UserCreated (
    UserExtendedDTO user
) {}
