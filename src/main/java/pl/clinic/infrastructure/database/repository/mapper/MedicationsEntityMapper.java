package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationsEntityMapper {

    MedicationsEntityMapper INSTANCE = Mappers.getMapper(MedicationsEntityMapper.class);

    Medications mapFromEntity(MedicationsEntity entity);

    MedicationsEntity mapToEntity(Medications medications);
}
