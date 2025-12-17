package synapt.flitsfiets.common.events.users;

import synapt.flitsfiets.common.dto.user.UserFullDTO;

public record UserOnboarded (
    UserFullDTO user
) {}
