package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

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

  default   UserRegistrationDto mapToDto(User user){
        return UserRegistrationDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .active(user.getActive())
                .role(user.getRoles().stream()
                        .map(Role::getRole)
                        .collect(Collectors.joining(",")))
                .build();
  };
}


