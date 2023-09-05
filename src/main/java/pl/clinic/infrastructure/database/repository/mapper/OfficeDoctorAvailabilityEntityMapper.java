package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfficeDoctorAvailabilityEntityMapper {

    @Mapping(target = "office", ignore = true)
    OfficeDoctorAvailability mapFromEntity(OfficeDoctorAvailabilityEntity entity);

    default OfficeDoctorAvailability mapFromEntityWithOffice(OfficeDoctorAvailabilityEntity entity){
        return mapFromEntity(entity)
                .withOffice(OfficeEntityMapper.INSTANCE.mapFromEntity(entity.getOffice()));
    }

    OfficeDoctorAvailabilityEntity mapToEntity(OfficeDoctorAvailability availability);
}