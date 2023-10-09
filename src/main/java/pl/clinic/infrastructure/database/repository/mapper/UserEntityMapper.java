package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.RoleEntity;
import pl.clinic.infrastructure.database.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

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
    default UserEntity mapToEntityWith(User user) {
        Set<RoleEntity> roleEntities = user.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .roleId(role.getRoleId())
                        .role(role.getRole())
                        .build())
                .collect(Collectors.toSet());

        return UserEntity.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .roles(roleEntities)
                .patient(null)
                .doctors(null)
                .build();
    }


    @Mapping(target = "doctors", ignore = true)
    @Mapping(target = "patient", ignore = true)
    User mapFromEntity(UserEntity entity);

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
    default User mapFromEntityForPatient(UserEntity entity) {
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
                .patient(PatientsEntityMapper.INSTANCE.mapFromEntity(entity.getPatient()))
                .build();
    }


    default UserEntity MapToEntityForPatient(User user) {
        Set<RoleEntity> roleEntities = user.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .roleId(3)
                        .role(role.getRole())
                        .build())
                .collect(Collectors.toSet());

        return UserEntity.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .roles(roleEntities)
                .patient(null)
                .doctors(null)
                .build();
    }


}



