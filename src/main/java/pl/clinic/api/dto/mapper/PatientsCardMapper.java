package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.domain.PatientCard;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PatientsCardMapper {


    PatientsCardMapper INSTANCE = Mappers.getMapper(PatientsCardMapper.class);

    default PatientCard mapFromDto(PatientCardDTO patientCardDTO) {
        return PatientCard.builder()
                .diagnosisDate(patientCardDTO.getDiagnosisDate())
                .diagnosisNote(patientCardDTO.getDiagnosisNote())
                .doctor(DoctorMapper.INSTANCE.mapFromDtoForPatientCard(patientCardDTO.getDoctor()))
                .patient(PatientsMapper.INSTANCE.mapFromDtoWithoutAppointment(patientCardDTO.getPatient()))
                .diseases(patientCardDTO.getDiseases().stream()
                        .map(DiseasesMapper.INSTANCE::mapFromDtoWithoutPatientCard)
                        .collect(Collectors.toSet()))
                .prescription(PrescriptionMapper.INSTANCE.mapFromDto(patientCardDTO.getPrescription()))
                .build();
    }

    default PatientCardDTO mapToDtoWithDoc(PatientCard patientCard) {
        return PatientCardDTO.builder()
                .patientCardId(patientCard.getPatientCardId())
                .diagnosisDate(patientCard.getDiagnosisDate())
                .diagnosisNote(patientCard.getDiagnosisNote())
                .doctor(DoctorMapper.INSTANCE.mapToDto(patientCard.getDoctor()))
                .diseases(patientCard.getDiseases().stream()
                        .map(DiseasesMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .prescription(PrescriptionMapper.INSTANCE.mapToDto(patientCard.getPrescription()))
                .build();
    }
}
