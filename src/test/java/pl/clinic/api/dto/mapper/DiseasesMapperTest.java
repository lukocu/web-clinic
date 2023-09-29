package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.DiseasesDTO;
import pl.clinic.domain.Diseases;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class DiseasesMapperTest {

    @Test
    public void testMapFromDtoWithoutPatientCard() {

        DiseasesDTO diseasesDTO = DTOFixtures.fluDisease();


        Diseases result = DiseasesMapper.INSTANCE.mapFromDtoWithoutPatientCard(diseasesDTO);


        assertEquals(diseasesDTO.getDiseaseName(), result.getDiseaseName());
        assertEquals(diseasesDTO.getDiseaseDescription(), result.getDiseaseDescription());
    }

    @Test
    public void testMapToDto() {

        Diseases diseases = DomainData.fluDisease();


        DiseasesDTO result = DiseasesMapper.INSTANCE.mapToDto(diseases);


        assertEquals(diseases.getDiseaseName(), result.getDiseaseName());
        assertEquals(diseases.getDiseaseDescription(), result.getDiseaseDescription());
    }


    @Test
    public void testMapFromDtoWithoutPatientCardWithEmptyValues() {
        // Create a DiseasesDTO object with empty values
        DiseasesDTO diseasesDTO = new DiseasesDTO("", "");

        // Map from DTO using mapFromDtoWithoutPatientCard
        Diseases result = DiseasesMapper.INSTANCE.mapFromDtoWithoutPatientCard(diseasesDTO);

        // Assert that the result has the expected empty values
        assertEquals("", result.getDiseaseName());
        assertEquals("", result.getDiseaseDescription());
    }

    @Test
    public void testMapToDtoWithEmptyValues() {

        Diseases diseases = Diseases.builder()
                .diseaseId(100)
                .diseaseName("")
                .diseaseDescription("")
                .build();


        DiseasesDTO result = DiseasesMapper.INSTANCE.mapToDto(diseases);


        assertEquals("", result.getDiseaseName());
        assertEquals("", result.getDiseaseDescription());
    }
}
