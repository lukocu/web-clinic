package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorsEntityMapper {

    @Mapping(target = "specializations",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "patientCards",ignore = true)
    Doctors mapFromEntity(DoctorsEntity entity);
}
