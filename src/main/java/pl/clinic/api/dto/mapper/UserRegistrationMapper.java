package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {
    default User mapFromDto(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .username(userRegistrationDto.getUsername())
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .active(true)
                .roles(Set.of(Role.builder()
                        .role(userRegistrationDto.getRole())
                        .build()))
                .build();

    }
}


