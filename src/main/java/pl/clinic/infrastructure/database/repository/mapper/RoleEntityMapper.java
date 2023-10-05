package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Role;
import pl.clinic.infrastructure.database.entity.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
   default Role mapFromEntity(RoleEntity entity){
       return Role.builder()
               .roleId(entity.getRoleId())
               .role(entity.getRole())
               .build();
   }

}
