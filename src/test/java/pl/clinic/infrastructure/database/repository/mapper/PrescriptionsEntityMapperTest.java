package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.entity.PrescriptionsEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionsEntityMapperTest {

    @Test
    public void testMapFromEntity() {

        PrescriptionsEntity entity = EntityFixtures.prescription1();


        Prescriptions result = PrescriptionsEntityMapper.INSTANCE.mapFromEntity(entity);


        assertEquals(entity.getPrescriptionDate(), result.getPrescriptionDate());
        assertEquals(entity.getPrescriptionDateEnd(), result.getPrescriptionDateEnd());
        assertEquals(entity.getPrescriptionAvailable(), result.getPrescriptionAvailable());
    }

    @Test
    public void testMapFromEntityWithMedications() {

        PrescriptionsEntity entity = EntityFixtures.prescription2();


        Prescriptions result = PrescriptionsEntityMapper.INSTANCE.mapFromEntityWithMedications(entity);


        assertEquals(entity.getPrescriptionDate(), result.getPrescriptionDate());
        assertEquals(entity.getPrescriptionDateEnd(), result.getPrescriptionDateEnd());
        assertEquals(entity.getPrescriptionAvailable(), result.getPrescriptionAvailable());
        assertNotNull(result.getMedications());
        assertEquals(2, result.getMedications().size());
    }

    @Test
    public void testMapToEntity() {

        Prescriptions prescriptions = DomainData.prescription1();


        PrescriptionsEntity result = PrescriptionsEntityMapper.INSTANCE.mapToEntity(prescriptions);


        assertEquals(prescriptions.getPrescriptionDate(), result.getPrescriptionDate());
        assertEquals(prescriptions.getPrescriptionDateEnd(), result.getPrescriptionDateEnd());
        assertEquals(prescriptions.getPrescriptionAvailable(), result.getPrescriptionAvailable());
    }

    @Test
    public void testMapToEntityWithMedications() {
        // Create a Prescriptions object with Medications
        Prescriptions prescriptions = DomainData.prescription2();

        // Map to entity using mapToEntity
        PrescriptionsEntity result = PrescriptionsEntityMapper.INSTANCE.mapToEntity(prescriptions);

        // Assert that the result has the expected values
        assertEquals(prescriptions.getPrescriptionDate(), result.getPrescriptionDate());
        assertEquals(prescriptions.getPrescriptionDateEnd(), result.getPrescriptionDateEnd());
        assertEquals(prescriptions.getPrescriptionAvailable(), result.getPrescriptionAvailable());
        assertNotNull(result.getMedications());
        assertEquals(2, result.getMedications().size());
    }
}
