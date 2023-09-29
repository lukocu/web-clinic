package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.Specialization;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.*;

public class SpecializationEntityMapperTest {

    @Test
    public void testMapFromEntity() {

        SpecializationEntity entity = EntityFixtures.cardiologySpecializationEntity();


        Specialization result = SpecializationEntityMapper.INSTANCE.mapFromEntity(entity);


        assertEquals(entity.getSpecializationId(), result.getSpecializationId());
        assertEquals(entity.getSpecializationName(), result.getSpecializationName());
    }

    @Test
    public void testMapToEntity() {

        Specialization specialization = DomainData.pediatricsSpecialization();


        SpecializationEntity result = SpecializationEntityMapper.INSTANCE.mapToEntity(specialization);


        assertEquals(specialization.getSpecializationId(), result.getSpecializationId());
        assertEquals(specialization.getSpecializationName(), result.getSpecializationName());
    }

}
