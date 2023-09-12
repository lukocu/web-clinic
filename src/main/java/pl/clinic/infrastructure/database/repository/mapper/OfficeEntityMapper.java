package pl.clinic.infrastructure.database.repository.mapper;

import jakarta.persistence.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfficeEntityMapper {

    OfficeEntityMapper INSTANCE = Mappers.getMapper(OfficeEntityMapper.class);

    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "officeDoctorAvailabilities", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    Office mapFromEntity(OfficeEntity entity);

    @InheritInverseConfiguration
    OfficeEntity mapToEntity(Office office);

    default Office mapFromEntityWithoutAppointments(OfficeEntity entity) {
        return Office.builder()
                .officeId(entity.getOfficeId())
                .firstConsultationFee(entity.getFirstConsultationFee())
                .followupConsultationFee(entity.getFollowupConsultationFee())
                .doctor(DoctorsEntityMapper.INSTANCE.mapFromEntity(entity.getDoctor()))
                .officeDoctorAvailabilities(entity.getOfficeDoctorAvailabilities().stream()
                        .map(OfficeDoctorAvailabilityEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()))
                .build();

    }


}

