package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MedicationsJpaRepositoryTest {

    private MedicationsJpaRepository medicationsJpaRepository;

    @Test
    void testFindByMedicationName() {
        // given
        String medicationName = "Aspirin";

        Optional<MedicationsEntity> foundMedication = medicationsJpaRepository.findByMedicationName(medicationName);

        // when, then
        assertTrue(foundMedication.isPresent());
        assertEquals(medicationName, foundMedication.get().getMedicationName());
    }

    @Test
    void testFindByMedicationName_NotFound() {

        String medicationName = "NonExistentMedication";


        Optional<MedicationsEntity> foundMedication = medicationsJpaRepository.findByMedicationName(medicationName);


        assertFalse(foundMedication.isPresent());
    }
}


