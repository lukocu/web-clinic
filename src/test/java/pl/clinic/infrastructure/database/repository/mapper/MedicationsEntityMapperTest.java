package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;
import pl.clinic.infrastructure.database.repository.mapper.MedicationsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MedicationsEntityMapperTest {

    private final MedicationsEntityMapper mapper = Mappers.getMapper(MedicationsEntityMapper.class);

    @Test
    public void testMapFromEntity() {

        MedicationsEntity entity = EntityFixtures.antibioticMedicationsEntity();

        Medications medications = mapper.mapFromEntity(entity);

        assertEquals(entity.getMedicationId(), medications.getMedicationId());
        assertEquals(entity.getMedicationName(), medications.getMedicationName());
        assertEquals(entity.getDosage(), medications.getDosage());
        assertEquals(entity.getFrequency(), medications.getFrequency());
        assertEquals(entity.getDuration(), medications.getDuration());
    }


    @Test
    public void testMapToEntity() {

        Medications medications = DomainData.aspirinMedications();

        MedicationsEntity entity = mapper.mapToEntity(medications);

        assertEquals(medications.getMedicationId(), entity.getMedicationId());
        assertEquals(medications.getMedicationName(), entity.getMedicationName());
        assertEquals(medications.getDosage(), entity.getDosage());
        assertEquals(medications.getFrequency(), entity.getFrequency());
        assertEquals(medications.getDuration(), entity.getDuration());
    }

}
