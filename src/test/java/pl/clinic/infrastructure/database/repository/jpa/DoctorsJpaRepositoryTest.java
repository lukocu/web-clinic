package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;
import pl.clinic.util.EntityFixtures;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorsJpaRepositoryTest {

    private DoctorsJpaRepository doctorsJpaRepository;


    @Test
    public void testFindDoctorsAndOffice() {

        List<DoctorsEntity> doctorsWithOffice = doctorsJpaRepository.findDoctorsAndOffice();


    var specializationEntities = new HashSet<>();
    var officeEntities = new HashSet<>();



        assertTrue(!doctorsWithOffice.isEmpty());
        for (DoctorsEntity doctor : doctorsWithOffice) {
            if(doctor.getSpecializations().size() > 0)
                 specializationEntities.add(doctor.getSpecializations().stream()
                         .map(SpecializationEntity::getSpecializationName)
                         .collect(Collectors.toSet()));

            if(doctor.getOffices().size() > 0);
            officeEntities.add(doctor.getOffices().stream()
                    .map(OfficeEntity::getOfficeId)
                    .collect(Collectors.toSet()));
        }

        assertTrue(!doctorsWithOffice.isEmpty());
        assertTrue(!specializationEntities.isEmpty());
        assertTrue(!officeEntities.isEmpty());
    }

    @Test
    public void testFindByUserId() {
        Optional<DoctorsEntity> loadedDoctor = doctorsJpaRepository.findByUserId(3);
        Integer doctorId = loadedDoctor.get().getDoctorId();

        assertTrue(loadedDoctor.isPresent());

        assertEquals(Integer.valueOf(2),doctorId);
    }
}


