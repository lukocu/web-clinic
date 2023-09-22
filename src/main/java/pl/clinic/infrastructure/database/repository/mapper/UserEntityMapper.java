package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.RoleEntity;
import pl.clinic.infrastructure.database.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    default UserEntity mapToEntityWithNewPatient(User user) {
        Set<RoleEntity> roleEntities = user.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .role(role.getRole())
                        .build())
                .collect(Collectors.toSet());

        return UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .roles(roleEntities)
                .patient(PatientsEntityMapper.INSTANCE.mapToEntity(user.getPatient()))
                .doctors(null)
                .build();
    }


    default User mapFromEntity(UserEntity entity) {
        Set<Role> roles = entity.getRoles().stream()
                .map(role -> Role.builder()
                        .roleId(role.getRoleId())
                        .role(role.getRole())
                        .build())
                .collect(Collectors.toSet());

        return User.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .active(entity.getActive())
                .roles(roles)
                .doctors(DoctorsEntityMapper.INSTANCE.mapFromEntity(entity.getDoctors()))
                .patient(PatientsEntityMapper.INSTANCE.mapFromEntity(entity.getPatient()))
                .build();
    }
    default User mapFromEntityForDoctor(UserEntity entity) {
        Set<Role> roles = entity.getRoles().stream()
                .map(role -> Role.builder()
                        .roleId(role.getRoleId())
                        .role(role.getRole())
                        .build())
                .collect(Collectors.toSet());

        return User.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .active(entity.getActive())
                .roles(roles)
                .doctors(DoctorsEntityMapper.INSTANCE.mapFromEntity(entity.getDoctors()))
                .build();
    }


}



