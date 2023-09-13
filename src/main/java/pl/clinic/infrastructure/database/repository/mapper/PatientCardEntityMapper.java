package pl.clinic.infrastructure.database.repository.mapper;

import jakarta.persistence.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.entity.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientCardEntityMapper {

    PatientCardEntityMapper INSTANCE = Mappers.getMapper(PatientCardEntityMapper.class);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "diseases", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    PatientCard mapFromEntity(PatientCardEntity entity);

    default PatientCard mapFromEntityWithFields(PatientCardEntity entity) {
        return mapFromEntity(entity)
                .withPatient(PatientsEntityMapper.INSTANCE.mapFromEntity(entity.getPatient()))
                .withDiseases(entity.getDiseases().stream()
                        .map(DiseasesEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()))
                .withPrescription(PrescriptionsEntityMapper.INSTANCE.mapFromEntity(entity.getPrescription()));
    }

    PatientCardEntity mapToEntity(PatientCard existingPatientCard);

    default PatientCardEntity mapToEntityWithFields(PatientCard existingPatientCard) {
        return PatientCardEntity.builder()
                .patientCardId(existingPatientCard.getPatientCardId())
                .diagnosisDate(existingPatientCard.getDiagnosisDate())
                .diagnosisNote(existingPatientCard.getDiagnosisNote())
                .patient(PatientsEntityMapper.INSTANCE.mapToEntity(existingPatientCard.getPatient()))
                .doctor(DoctorsEntityMapper.INSTANCE.mapToEntity(existingPatientCard.getDoctor()))
                .diseases(existingPatientCard.getDiseases().stream()
                        .map(DiseasesEntityMapper.INSTANCE::mapToEntity)
                        .collect(Collectors.toSet()))
                .prescription(PrescriptionsEntityMapper.INSTANCE.mapToEntity(existingPatientCard.getPrescription()))
                .build();

    }
}
