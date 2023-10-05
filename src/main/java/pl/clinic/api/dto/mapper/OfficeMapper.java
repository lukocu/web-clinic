package pl.clinic.api.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface OfficeMapper {
    OfficeMapper INSTANCE = Mappers.getMapper(OfficeMapper.class);


    @Mapping(target = "officeDoctorAvailability", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    OfficeDTO mapToDto(Office office);


    default OfficeDTO mapToDtoForDoctor(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .build();
    }


    default OfficeDTO mapToDtoWithDoctor(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .doctor(DoctorMapper.INSTANCE.mapToDto(office.getDoctor()))
                .build();
    }


    default Office mapFromDto(OfficeDTO officeDTO){
        return Office.builder()
                .officeId(officeDTO.getOfficeId())
                .firstConsultationFee(officeDTO.getFirstConsultationFee())
                .followupConsultationFee(officeDTO.getFollowupConsultationFee())
                .build();
    }
}
