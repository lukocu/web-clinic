package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationsEntityMapper {
    Medications mapFromEntity(MedicationsEntity entity);
}
