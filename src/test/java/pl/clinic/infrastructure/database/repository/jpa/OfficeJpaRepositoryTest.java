package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OfficeJpaRepositoryTest {

    private OfficeJpaRepository officeJpaRepository;

    @Test
    public void testFindAllByDoctorDoctorId() {

        List<OfficeEntity> officesForDoctor1 = officeJpaRepository.findAllByDoctorDoctorId(1);

        List<OfficeEntity> officesForDoctor2 = officeJpaRepository.findAllByDoctorDoctorId(2);

        List<OfficeEntity> officesForNonExistentDoctor = officeJpaRepository.findAllByDoctorDoctorId(100);

        assertEquals(0, officesForNonExistentDoctor.size());
        assertEquals(1, officesForDoctor1.size());
        assertEquals(1, officesForDoctor2.size());
    }
}
