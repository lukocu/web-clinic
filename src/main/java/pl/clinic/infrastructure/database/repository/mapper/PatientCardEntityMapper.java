package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientCardEntityMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "diseases", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    PatientCard mapFromEntity(PatientCardEntity entity);


    PatientCardEntity mapToEntity(PatientCard existingPatientCard);
}
