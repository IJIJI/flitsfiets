package synapt.flitsfiets.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

//        TypeMap<User, AuthUserDataDto> typeMap =
//                modelMapper.createTypeMap(User.class, AuthUserDataDto.class);
//
//        typeMap.setPostConverter(ctx -> {
//            User source = ctx.getSource();
//            AuthUserDataDto dest = ctx.getDestination();
//
//            var effectiveRoles = roleHierarchy
//                    .getReachableGrantedAuthorities(source.getAuthorities())
//                    .stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .toList();
//
//            dest.setRoles(effectiveRoles);
//            return dest;
//        });

        return modelMapper;
    }
}