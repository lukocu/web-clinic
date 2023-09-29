package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.PrescriptionsDTO;
import pl.clinic.api.dto.mapper.PrescriptionMapper;
import pl.clinic.domain.Prescriptions;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrescriptionMapperTest {

    private PrescriptionMapper prescriptionMapper=PrescriptionMapper.INSTANCE;



    @Test
    public void testMapFromDto() {
        // Given
        PrescriptionsDTO prescriptionsDTO = DTOFixtures.prescription1(); // Assuming DTOFixtures contains prescription1 data

        // When
        Prescriptions prescriptions = prescriptionMapper.mapFromDto(prescriptionsDTO);

        // Then
        assertEquals(prescriptionsDTO.getPrescriptionDate(), prescriptions.getPrescriptionDate());
        assertEquals(prescriptionsDTO.getPrescriptionDateEnd(), prescriptions.getPrescriptionDateEnd());
        assertEquals(prescriptionsDTO.getPrescriptionAvailable(), prescriptions.getPrescriptionAvailable());


        assertEquals(1, prescriptions.getMedications().size());
    }

    @Test
    public void testMapToDto() {
        // Given
        Prescriptions prescriptions = DomainData.prescription4();

        // When
        PrescriptionsDTO prescriptionsDTO = prescriptionMapper.mapToDto(prescriptions);

        // Then
        assertEquals(prescriptions.getPrescriptionDate(), prescriptionsDTO.getPrescriptionDate());
        assertEquals(prescriptions.getPrescriptionDateEnd(), prescriptionsDTO.getPrescriptionDateEnd());
        assertEquals(prescriptions.getPrescriptionAvailable(), prescriptionsDTO.getPrescriptionAvailable());


        assertEquals(1, prescriptionsDTO.getMedications().size());
    }
}
