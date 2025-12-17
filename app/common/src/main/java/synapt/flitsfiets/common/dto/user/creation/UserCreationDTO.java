package synapt.flitsfiets.common.dto.user.creation;


import synapt.flitsfiets.common.enums.BikeType;
import synapt.flitsfiets.common.enums.PlanType;

public class UserCreationDTO
{
    UserCreationPersonalDTO personal;
    UserCreationAddressDTO address;
    UserCreationContactDTO contact;
    BikeType bikeType;
    PlanType planType;
    String pickupCity;

}

