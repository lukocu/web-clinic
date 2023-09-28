package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OfficeDoctorAvailabilityJpaRepositoryTest {

    private OfficeDoctorAvailabilityJpaRepository officeDoctorAvailabilityJpaRepository;
    private OfficeJpaRepository officeJpaRepository;

    @Test
    void testFindAvailableHoursForDoctor() {

        String name = "John";
        String surname = "Smith";


        List<OfficeDoctorAvailabilityEntity> availableHours = officeDoctorAvailabilityJpaRepository.findAvailableHoursForDoctor(name, surname);


        assertFalse(availableHours.isEmpty());
    }

    @Test
    void testFindByOfficeIdAndAvailabilityStatusIsTrue() {

        Integer officeId = 2;

        List<OfficeDoctorAvailabilityEntity> availableHours =
                officeDoctorAvailabilityJpaRepository.findByOfficeIdAndAvailabilityStatusIsTrue(officeId);


        assertFalse(availableHours.isEmpty());
        assertEquals(2,availableHours.size());
    }

    @Test
    void testFindUnavailableStatusForDoctor() {

        Integer doctorId = 1;
        Integer doctorId2 = 2;


        List<OfficeDoctorAvailabilityEntity> unavailableStatus1 =
                officeDoctorAvailabilityJpaRepository.findUnavailableStatusForDoctor(doctorId);

        List<OfficeDoctorAvailabilityEntity> unavailableStatus2 =
                officeDoctorAvailabilityJpaRepository.findUnavailableStatusForDoctor(doctorId2);


        assertTrue(unavailableStatus1.isEmpty());
        assertFalse(unavailableStatus2.isEmpty());
        assertEquals(2,unavailableStatus2.size());
    }

    @Test
    void testDeleteByIdCustom() {

        officeDoctorAvailabilityJpaRepository.deleteByIdCustom(1);
        officeDoctorAvailabilityJpaRepository.deleteByIdCustom(2);


        Optional<OfficeDoctorAvailabilityEntity> deletedEntity = officeDoctorAvailabilityJpaRepository.findById( 2);
        Optional<OfficeDoctorAvailabilityEntity> deletedEntity2 = officeDoctorAvailabilityJpaRepository.findById(1);
        assertFalse(deletedEntity.isPresent());
        assertFalse(deletedEntity2.isPresent());
    }

    @Test
    void testFindByDateAndTimeRange() {

        LocalDate date = LocalDate.of(2023, 8, 17);
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        Integer officeId = 2;


        List<OfficeDoctorAvailabilityEntity> results = officeDoctorAvailabilityJpaRepository.findByDateAndTimeRange(date, startTime, endTime, officeId);


        assertFalse(results.isEmpty());


        assertFalse(results.stream().allMatch(OfficeDoctorAvailabilityEntity::getAvailabilityStatus));


        assertEquals(3, results.size());
    }

    @Test
    void testFindConflictingAppointments_ShouldReturnedConflict() {

        LocalDate date = LocalDate.of(2023, 8, 17);
        LocalTime startTime = LocalTime.of(15, 30);
        LocalTime endTime = LocalTime.of(16, 30);
        Integer officeId = 2;


        List<OfficeDoctorAvailabilityEntity> results = officeDoctorAvailabilityJpaRepository.findConflictingAppointments(date, startTime, endTime, officeId);


        assertFalse(results.isEmpty());


        for (OfficeDoctorAvailabilityEntity result : results) {
            assertTrue(result.getAvailabilityStatus());
        }
    }

    @Test
    void testFindByDateAndTime() {

        LocalDate date = LocalDate.of(2023, 8, 17);
        LocalTime startTime = LocalTime.of(16, 0);
        Integer officeId = 2;


        Optional<OfficeDoctorAvailabilityEntity> result = officeDoctorAvailabilityJpaRepository.findByDateAndTime(date, startTime, officeId);


        assertTrue(result.isPresent());


        assertTrue(result.get().getAvailabilityStatus());
    }



}
