package synapt.flitsfiets.common.dto.user.creation;


import lombok.Data;
import lombok.ToString;
import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.Location;
import synapt.flitsfiets.common.enums.PlanType;

@Data
@ToString
public class UserCreationDTO
{
    UserCreationPersonalDTO personal;
    UserCreationAddressDTO address;
    UserCreationContactDTO contact;
    BikeType bikeType;
    PlanType planType;
    Location pickupCity;
}

