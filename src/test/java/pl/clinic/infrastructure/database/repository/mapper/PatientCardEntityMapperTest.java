package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.*;

public class PatientCardEntityMapperTest {

    @Test
    public void testMapFromEntityWithFieldsForDoc() {

        PatientCardEntity entity = EntityFixtures.patientCardEntity1();


        PatientCard result = PatientCardEntityMapper.INSTANCE.mapFromEntityWithFieldsForDoc(entity);


        assertEquals(entity.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(entity.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getPatient());
        assertNotNull(result.getPrescription());
        assertEquals(2, result.getDiseases().size());
    }

    @Test
    public void testMapFromEntityWithFields() {

        PatientCardEntity entity = EntityFixtures.patientCardEntity4();


        PatientCard result = PatientCardEntityMapper.INSTANCE.mapFromEntityWithFields(entity);


        assertEquals(entity.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(entity.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getDoctor());
        assertEquals(1, result.getDiseases().size());
    }

    @Test
    public void testMapToEntityWithFields() {

        PatientCard patientCard = DomainData.patientCard2();


        PatientCardEntity result = PatientCardEntityMapper.INSTANCE.mapToEntityWithFields(patientCard);


        assertEquals(patientCard.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(patientCard.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getPatient());
        assertNotNull(result.getPrescription());
        assertEquals(1, result.getDiseases().size());
    }

    @Test
    public void testMapToEntityWithFieldsMultipleDiseases() {

        PatientCard patientCard = DomainData.patientCard1();


        PatientCardEntity result = PatientCardEntityMapper.INSTANCE.mapToEntityWithFields(patientCard);


        assertEquals(patientCard.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(patientCard.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getPatient());
        assertNotNull(result.getPrescription());
        assertEquals(2, result.getDiseases().size());
    }
}
