package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.PrescriptionsDTO;
import pl.clinic.domain.Medications;
import pl.clinic.domain.Prescriptions;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

   default Prescriptions mapFromDto(PrescriptionsDTO prescription){
       return Prescriptions.builder()
               .prescriptionDate(prescription.getPrescriptionDate())
               .prescriptionDateEnd(prescription.getPrescriptionDateEnd())
               .prescriptionAvailable(prescription.getPrescriptionAvailable())
               .medications(prescription.getMedications().stream()
                       .map(MedicationsMapper.INSTANCE::mapFromDto)
                       .collect(Collectors.toSet()))
               .build();
   }

  default   PrescriptionsDTO mapToDto(Prescriptions prescription){
       return PrescriptionsDTO.builder()
              .prescriptionDate(prescription.getPrescriptionDate())
              .prescriptionDateEnd(prescription.getPrescriptionDateEnd())
              .prescriptionAvailable(prescription.getPrescriptionAvailable())
              .medications(prescription.getMedications().stream()
                      .map(MedicationsMapper.INSTANCE::mapToDto)
                      .collect(Collectors.toSet()))
              .build();

  }
}
