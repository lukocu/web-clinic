package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.time.LocalDate;
import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface OfficeDoctorAvailabilityMapper {

    default OfficeDoctorAvailabilityDTO mapToDtoWithOfficeId(OfficeDoctorAvailability officeDoctorAvailability) {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(officeDoctorAvailability.getOfficeAvailabilityId())
                .date(officeDoctorAvailability.getDate())
                .startTime(officeDoctorAvailability.getStartTime())
                .endTime(officeDoctorAvailability.getEndTime())
                .availabilityStatus(officeDoctorAvailability.getAvailabilityStatus())
                .officeId(officeDoctorAvailability.getOffice().getOfficeId())
                .build();
    }
}
