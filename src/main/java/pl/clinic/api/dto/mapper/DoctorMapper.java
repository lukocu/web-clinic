package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.SpecializationDTO;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.Specialization;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DoctorMapper {



   default DoctorDTO  mapAdditionalFields(final Doctors doctor){
       return DoctorDTO.builder()
               .name(doctor.getName())
               .surname(doctor.getSurname())
               .phone(doctor.getPhone())
               .specializationNames(getSpecializations(doctor))
               .offices(getOffices(doctor))
               .build();

   }

    private static Set<Integer> getOffices(Doctors doctor) {
        if (doctor != null && doctor.getOffices() != null) {
            return doctor.getOffices().stream()
                    .map(Office::getOfficeId)
                    .collect(Collectors.toSet());
        } else {
            return Collections.emptySet(); // Zwracamy pustą kolekcję, jeśli obiekt lub pola są nullami.
        }
    }

    private static String getSpecializations(Doctors doctor) {
        if (doctor.getSpecializations() != null) {
            return doctor.getSpecializations().stream()
                    .map(Specialization::getSpecializationName)
                    .collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }


}
