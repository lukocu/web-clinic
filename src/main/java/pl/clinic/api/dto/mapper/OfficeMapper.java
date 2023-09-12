package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.Office;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface OfficeMapper {
    OfficeMapper INSTANCE = Mappers.getMapper(OfficeMapper.class);
    default  OfficeDTO mapToDtoWithoutAppointment(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .doctor(DoctorMapper.INSTANCE.mapAdditionalFields(office.getDoctor()))
                .officeDoctorAvailability(office.getOfficeDoctorAvailabilities().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

}
