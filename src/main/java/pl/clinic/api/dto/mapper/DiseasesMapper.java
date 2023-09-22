package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.DiseasesDTO;
import pl.clinic.domain.Diseases;

@Mapper(componentModel = "spring")
public interface DiseasesMapper {

    DiseasesMapper INSTANCE = Mappers.getMapper(DiseasesMapper.class);


    default Diseases mapFromDtoWithoutPatientCard(DiseasesDTO diseases) {
        return Diseases.builder()
                .diseaseName(diseases.getDiseaseName())
                .diseaseDescription(diseases.getDiseaseDescription())
                .build();

    }

  default   DiseasesDTO mapToDto(Diseases diseases){
        return DiseasesDTO.builder()
                .diseaseName(diseases.getDiseaseName())
                .diseaseDescription(diseases.getDiseaseDescription())
                .build();
  }
}
