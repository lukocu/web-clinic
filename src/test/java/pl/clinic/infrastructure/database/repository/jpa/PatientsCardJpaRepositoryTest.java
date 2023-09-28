package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.business.dao.PatientsRepository;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientsCardJpaRepositoryTest {

    private PatientCardJpaRepository patientCardJpaRepository;

    @Test
    public void testFindByPatientIdWithDetails() {


        Optional<PatientCardEntity> cardForPatientId1 = patientCardJpaRepository.findById(1);
        Integer patientId = cardForPatientId1.get().getPatient().getPatientId();

        List<PatientCardEntity> patientCardsForPatient1 = patientCardJpaRepository.findByPatientIdWithDetails(patientId);

        PatientCardEntity firstPatientCard = patientCardsForPatient1.get(0);

        List<PatientCardEntity> patientCardsForPatient4 = patientCardJpaRepository.findByPatientIdWithDetails(4);

        PatientCardEntity firstPatientCardForPatient4 = patientCardsForPatient4.get(0);
        PatientCardEntity secondPatientCardForPatient4 = patientCardsForPatient4.get(1);


        List<PatientCardEntity> patientCardsForNonExistentPatient =
                patientCardJpaRepository.findByPatientIdWithDetails(100);


        List<PatientCardEntity> patientCardsForPatient2 =
                patientCardJpaRepository.findByPatientIdWithDetails(3);

        assertEquals(1, patientCardsForPatient1.size());

        assertNotNull(firstPatientCard.getPatient());
        assertNotNull(firstPatientCard.getDoctor());
        assertNotNull(firstPatientCard.getDiseases());
        assertNotNull(firstPatientCard.getPrescription());
        assertNotNull(firstPatientCard.getPrescription().getMedications());

        assertEquals(2, patientCardsForPatient4.size());

        assertNotNull(firstPatientCardForPatient4.getPatient());
        assertNotNull(firstPatientCardForPatient4.getDoctor());
        assertNotNull(firstPatientCardForPatient4.getDiseases());
        assertNotNull(firstPatientCardForPatient4.getPrescription());
        assertNotNull(firstPatientCardForPatient4.getPrescription().getMedications());

        assertNotNull(secondPatientCardForPatient4.getPatient());
        assertNotNull(secondPatientCardForPatient4.getDoctor());
        assertNotNull(secondPatientCardForPatient4.getDiseases());
        assertNotNull(secondPatientCardForPatient4.getPrescription());
        assertNotNull(secondPatientCardForPatient4.getPrescription().getMedications());

        assertEquals(0, patientCardsForNonExistentPatient.size());

        assertEquals(0, patientCardsForPatient2.size());

    }
}
