package pl.clinic.infrastructure.database.repository.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfficeDoctorAvailabilityEntityMapper {

    OfficeDoctorAvailabilityEntityMapper INSTANCE = Mappers.getMapper(OfficeDoctorAvailabilityEntityMapper.class);

    @Mapping(target = "office", ignore = true)
    OfficeDoctorAvailability mapFromEntity(OfficeDoctorAvailabilityEntity entity);

    default OfficeDoctorAvailability mapFromEntityWithOffice(OfficeDoctorAvailabilityEntity entity) {
        return mapFromEntity(entity)
                .withOffice(OfficeEntityMapper.INSTANCE.mapFromEntity(entity.getOffice()));
    }

    @Mapping(target = "office", ignore = true)
    OfficeDoctorAvailabilityEntity mapToEntity(OfficeDoctorAvailability availability);

    default OfficeDoctorAvailabilityEntity mapToEntityWithOffice(OfficeDoctorAvailability availability) {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(availability.getOfficeAvailabilityId())
                .date(availability.getDate())
                .startTime(availability.getStartTime())
                .endTime(availability.getEndTime())
                .availabilityStatus(availability.getAvailabilityStatus())
                .office(OfficeEntityMapper.INSTANCE.mapToEntity(availability.getOffice()))
                .build();

    }
}
