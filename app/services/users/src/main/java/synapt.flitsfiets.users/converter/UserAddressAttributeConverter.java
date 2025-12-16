//package synapt.flitsfiets.users.converter;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import org.springframework.lang.Contract;
//import synapt.flitsfiets.common.valueObject.UserAddress;
//import tools.jackson.databind.ObjectMapper;
//
//@Converter(autoApply = true)
//public class UserAddressAttributeConverter implements AttributeConverter<UserAddress, String> {
//
//    private static final ObjectMapper om = new ObjectMapper();
//
//    @Override
//    public String convertToDatabaseColumn(UserAddress attribute) {
//        if (attribute == null)
//            return null;
//
//        try{
//            return om.writeValueAsString(attribute);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Could not convert UserAddress to JSON string", e);
//        }
//    }
//
//    @Override
//    public UserAddress convertToEntityAttribute(String dbData) {
//        if (dbData == null)
//            return null;
//
//        try{
//            return om.readValue(dbData, UserAddress.class);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Could not convert JSON string to UserAddress", e);
//        }
//    }
//
//}
