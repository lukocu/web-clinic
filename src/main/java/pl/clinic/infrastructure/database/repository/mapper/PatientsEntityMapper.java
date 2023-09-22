package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientsEntityMapper {

    PatientsEntityMapper INSTANCE = Mappers.getMapper(PatientsEntityMapper.class);

    default Patients mapFromEntity(PatientsEntity entity){
        return Patients.builder()
                .patientId(entity.getPatientId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .pesel(entity.getPesel())
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }

    default PatientsEntity mapToEntity(Patients patient){
        return PatientsEntity.builder()
                .patientId(patient.getPatientId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .birthDate(patient.getBirthDate())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .build();
    }

}
