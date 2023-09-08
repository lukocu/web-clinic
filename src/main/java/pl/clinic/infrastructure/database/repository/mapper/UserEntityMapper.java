package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.RoleEntity;
import pl.clinic.infrastructure.database.entity.UserEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    default UserEntity mapToEntityWithNewPatient(User user) {
      return   UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .roles(Set.of(RoleEntity.builder()
                        .role(user.getRoles().toString())
                        .build()))
                .patient(PatientsEntityMapper.INSTANCE.mapToEntity(user.getPatient()))
                .doctors(null)
                .build();
    }


}


