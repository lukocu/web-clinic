package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.domain.PatientCard;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PatientsCardMapper {
  default  PatientCard mapFromDto(PatientCardDTO patientCardDTO) {
        return PatientCard.builder()
                .diagnosisDate(patientCardDTO.getDiagnosisDate())
                .diagnosisNote(patientCardDTO.getDiagnosisNote())
                .doctor(DoctorMapper.INSTANCE.mapFromDtoForPatientCard(patientCardDTO.getDoctor()))
                .patient(PatientsMapper.INSTANCE.mapFromDto(patientCardDTO.getPatient()))
                .diseases(patientCardDTO.getDiseases().stream()
                        .map(DiseasesMapper.INSTANCE::mapFromDtoWithoutPatientCard)
                        .collect(Collectors.toSet()))
                .prescription(PrescriptionMapper.INSTANCE.mapFromDto(patientCardDTO.getPrescription()))
                .build();
    }
}
