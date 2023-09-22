package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Diseases;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseasesEntityMapper {
    DiseasesEntityMapper INSTANCE = Mappers.getMapper(DiseasesEntityMapper.class);


    default Diseases mapFromEntity(DiseasesEntity entity) {
        return Diseases.builder()
                .diseaseId(entity.getDiseaseId())
                .diseaseDescription(entity.getDiseaseDescription())
                .diseaseName(entity.getDiseaseName())
                .build();
    }

    @InheritInverseConfiguration
    default DiseasesEntity mapToEntity(Diseases diseases) {
        return DiseasesEntity.builder()
                .diseaseId(diseases.getDiseaseId())
                .diseaseDescription(diseases.getDiseaseDescription())
                .diseaseName(diseases.getDiseaseName())
                .build();
    }
}
