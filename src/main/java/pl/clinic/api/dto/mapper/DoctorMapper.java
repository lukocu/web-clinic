package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Specialization;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DoctorMapper {


    @Mapping(target = "specializationNames", expression = "java(mapSpecializations(doctor.getSpecializations()))")
    @Mapping(target = "officeId", source = "office.officeId") // Przypisz id biura
    DoctorDTO mapAdditionalFields(final Doctors doctor);

    default String mapSpecializations(Set<Specialization> specializations) {
        return specializations.stream()
                .map(Specialization::getSpecializationName)
                .collect(Collectors.joining(", ")); // Łączymy nazwy specjalizacji przecinkiem
    }
}
