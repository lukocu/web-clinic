package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.MedicationsDTO;
import pl.clinic.domain.Medications;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class MedicationsMapperTest {

    @Test
    public void testMapFromDto() {

        MedicationsDTO medicationDTO = DTOFixtures.antibioticMedications();


        Medications result = MedicationsMapper.INSTANCE.mapFromDto(medicationDTO);


        assertEquals(medicationDTO.getMedicationName(), result.getMedicationName());
        assertEquals(medicationDTO.getDosage(), result.getDosage());
        assertEquals(medicationDTO.getFrequency(), result.getFrequency());
        assertEquals(medicationDTO.getFrequency(), result.getFrequency());
        assertEquals(medicationDTO.getDuration(), result.getDuration());
    }

    @Test
    public void testMapToDto() {

        Medications medication = DomainData.ibuprofenMedications();

        MedicationsDTO result = MedicationsMapper.INSTANCE.mapToDto(medication);

        assertEquals(medication.getMedicationName(), result.getMedicationName());
        assertEquals(medication.getDosage(), result.getDosage());
        assertEquals(medication.getFrequency(), result.getFrequency());
        assertEquals(medication.getFrequency(), result.getFrequency());
        assertEquals(medication.getDuration(), result.getDuration());
    }


}
