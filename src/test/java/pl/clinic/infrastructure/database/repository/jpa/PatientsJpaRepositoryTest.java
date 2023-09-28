package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;
import pl.clinic.util.EntityFixtures;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientsJpaRepositoryTest {

    private PatientsJpaRepository patientsJpaRepository;

    @Test
    public void testFindByPesel() {

        PatientsEntity patient = EntityFixtures.patientWithoutVisits();
        patient.setUser(EntityFixtures.patientUser());
        patient.setPesel("12345678901");
        patientsJpaRepository.save(patient);

        Optional<PatientsEntity> foundPatientOptional = patientsJpaRepository.findByPesel("12345678901");

        PatientsEntity foundPatient = foundPatientOptional.get();

        assertTrue(foundPatientOptional.isPresent());
        assertEquals("John", foundPatient.getName());
        assertEquals("Mago", foundPatient.getSurname());
        assertEquals("12345678901", foundPatient.getPesel());
    }

    @Test
    public void testFindByPeselWhenNotFound() {
        Optional<PatientsEntity> foundPatientOptional = patientsJpaRepository.findByPesel("99999999999");

        assertFalse(foundPatientOptional.isPresent());
    }

}
