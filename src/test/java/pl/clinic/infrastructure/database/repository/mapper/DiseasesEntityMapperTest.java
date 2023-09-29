package pl.clinic.infrastructure.database.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import pl.clinic.domain.Diseases;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

public class DiseasesEntityMapperTest {

    private final DiseasesEntityMapper mapper = DiseasesEntityMapper.INSTANCE;

    @Test
    public void testMapFromEntity() {

        DiseasesEntity entity = EntityFixtures.fluDiseaseEntity();



        Diseases disease = mapper.mapFromEntity(entity);


        assertEquals(entity.getDiseaseId(), disease.getDiseaseId());
        assertEquals(entity.getDiseaseDescription(), disease.getDiseaseDescription());
        assertEquals(entity.getDiseaseName(), disease.getDiseaseName());
    }

    @Test
    public void testMapToEntity() {

        Diseases disease = DomainData.feverDisease();


        DiseasesEntity entity = mapper.mapToEntity(disease);


        assertEquals(disease.getDiseaseId(), entity.getDiseaseId());
        assertEquals(disease.getDiseaseDescription(), entity.getDiseaseDescription());
        assertEquals(disease.getDiseaseName(), entity.getDiseaseName());
    }

    @Test
    public void testMapFromEntityWithNullValues() {

        DiseasesEntity entity = new DiseasesEntity();


        Diseases disease = mapper.mapFromEntity(entity);


        assertEquals(null, disease.getDiseaseId());
        assertEquals(null, disease.getDiseaseDescription());
        assertEquals(null, disease.getDiseaseName());
    }

    @Test
    public void testMapToEntityWithNullValues() {

        Diseases disease = Diseases.builder().build();


        DiseasesEntity entity = mapper.mapToEntity(disease);


        assertEquals(null, entity.getDiseaseId());
        assertEquals(null, entity.getDiseaseDescription());
        assertEquals(null, entity.getDiseaseName());
    }
}
