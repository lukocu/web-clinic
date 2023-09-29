package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.SpecializationDTO;
import pl.clinic.api.dto.mapper.SpecializationMapper;
import pl.clinic.domain.Specialization;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecializationMapperTest {

    private SpecializationMapper specializationMapper=SpecializationMapper.INSTANCE;


    @Test
    public void testMapToDto() {
        // Given
        Specialization specialization = DomainData.pediatricsSpecialization();

        // When
        SpecializationDTO specializationDTO = specializationMapper.mapToDto(specialization);

        // Then
        assertEquals(specialization.getSpecializationId(), specializationDTO.getSpecializationId());
        assertEquals(specialization.getSpecializationName(), specializationDTO.getSpecializationName());
    }

    @Test
    public void testMapFromDto() {
        // Given
        SpecializationDTO specializationDTO = DTOFixtures.cardiologySpecialization();

        // When
        Specialization specialization = specializationMapper.mapFromDto(specializationDTO);

        // Then
        assertEquals(specializationDTO.getSpecializationId(), specialization.getSpecializationId());
        assertEquals(specializationDTO.getSpecializationName(), specialization.getSpecializationName());
    }
}
