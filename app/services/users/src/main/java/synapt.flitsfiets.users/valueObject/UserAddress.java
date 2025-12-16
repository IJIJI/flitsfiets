package synapt.flitsfiets.users.valueObject;

import lombok.Value;

@Value
public class UserAddress {
    String street;
    String city;
    String postalCode;
    String country;

    public UserAddress(String street, String city, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return street + ", " + postalCode + " " + city + ", " + country;
    }
}
