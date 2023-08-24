package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfficeEntityMapper  {

    @Mapping(target = "appointments",ignore = true)
    Office mapFromEntity(OfficeEntity entity);
}
