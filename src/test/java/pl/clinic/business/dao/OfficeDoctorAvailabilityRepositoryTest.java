package pl.clinic.business.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.infrastructure.database.repository.jpa.OfficeDoctorAvailabilityJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.OfficeDoctorAvailabilityEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class OfficeDoctorAvailabilityRepositoryTest {

    @Mock
    private OfficeDoctorAvailabilityJpaRepository officeDoctorAvailabilityJpaRepository;

    @Mock
    private OfficeDoctorAvailabilityEntityMapper officeDoctorAvailabilityEntityMapper;

    @InjectMocks
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;

    @Test
    public void testFindAvailableHoursForDoctor() {
        // given
        String doctorName = "John";
        String doctorSurname = "Smith";
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor1();

        when(officeDoctorAvailabilityJpaRepository.findAvailableHoursForDoctor(doctorName, doctorSurname))
                .thenReturn(Collections.singletonList(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        List<OfficeDoctorAvailability> result =
                officeDoctorAvailabilityRepository.findAvailableHoursForDoctor(doctorName, doctorSurname);

        // then
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testSave() {
        // given
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_1();


        // when
        officeDoctorAvailabilityRepository.save(availability);

        // then
        verify(officeDoctorAvailabilityJpaRepository)
                .save(officeDoctorAvailabilityEntityMapper.mapToEntityWithOffice(availability));
    }

    @Test
    public void testFindByOfficeAndAvailabilityStatusIsTrue() {
        // given
        Integer officeId = 1;
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_2();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor2_2();

        when(officeDoctorAvailabilityJpaRepository.findByOfficeIdAndAvailabilityStatusIsTrue(officeId))
                .thenReturn(Collections.singletonList(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        List<OfficeDoctorAvailability> result =
                officeDoctorAvailabilityRepository.findByOfficeAndAvailabilityStatusIsTrue(officeId);

        // then
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }


    @Test
    public void testFindById() {
        // given

        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_4();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor2_4();

        when(officeDoctorAvailabilityJpaRepository.findById(availability.getOfficeAvailabilityId()))
                .thenReturn(Optional.of(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        Optional<OfficeDoctorAvailability> result
                = officeDoctorAvailabilityRepository.findById(availability.getOfficeAvailabilityId());

        // then
        assertTrue(result.isPresent());
        assertEquals(availability, result.get());
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testDeleteById() {
        // given
        Integer availabilityId = 1;

        // when
        officeDoctorAvailabilityRepository.deleteById(availabilityId);

        // then
        verify(officeDoctorAvailabilityJpaRepository).deleteByIdCustom(availabilityId);
    }

    @Test
    public void testFindAvailableStatusIsFalseWithDoctorId() {
        // given
        Integer doctorId = 1;
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_1();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor2_1();

        when(officeDoctorAvailabilityJpaRepository.findUnavailableStatusForDoctor(doctorId))
                .thenReturn(Collections.singletonList(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        List<OfficeDoctorAvailability> result
                = officeDoctorAvailabilityRepository.findAvailableStatusIsFalseWithDoctorId(doctorId);

        // then
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testFindByDateAndTimeRange() {
        // given
        LocalDate date = LocalDate.of(2023, 8, 17);
        LocalTime startTime = LocalTime.of(16, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        Integer officeId = 2;
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_2();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor2_2();

        when(officeDoctorAvailabilityJpaRepository.findByDateAndTimeRange(availability.getDate(),
                availability.getStartTime(),
                availability.getEndTime(),
                officeId))
                .thenReturn(Collections.singletonList(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        List<OfficeDoctorAvailability> result = officeDoctorAvailabilityRepository.findByDateAndTimeRange(date, startTime, endTime, officeId);

        // then
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testFindConflictingAppointments_shouldFindConflict() {
        // given
        LocalDate date = LocalDate.of(2023, 8, 17);
        LocalTime startTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        Integer officeId = 2;
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_3();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor2_3();

        when(officeDoctorAvailabilityJpaRepository.findConflictingAppointments(date,
                startTime, endTime, officeId))
                .thenReturn(Collections.singletonList(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        List<OfficeDoctorAvailability> result = officeDoctorAvailabilityRepository.findConflictingAppointments(date,
                startTime, endTime, officeId);

        // then
        assertEquals(1, result.size());
        assertEquals(availability, result.get(0));
        assertEquals(availability.getDate(), date);
        assertEquals(availability.getStartTime(), startTime);
        assertEquals(availability.getEndTime(), endTime);
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testFindByDateAndTime() {
        // given
        LocalDate date = LocalDate.of(2023, 8, 15);
        LocalTime startTime = LocalTime.of(8, 0, 0);
        Integer officeId = 1;
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();
        OfficeDoctorAvailabilityEntity availabilityEntity = EntityFixtures.officeAvailabilityDoctor1();

        when(officeDoctorAvailabilityJpaRepository.findByDateAndTime(date, startTime, officeId))
                .thenReturn(Optional.of(availabilityEntity));


        when(officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(any(OfficeDoctorAvailabilityEntity.class)))
                .thenReturn(availability);

        // when
        Optional<OfficeDoctorAvailability> result = officeDoctorAvailabilityRepository.findByDateAndTime(date, startTime, officeId);

        // then
        assertTrue(result.isPresent());
        assertEquals(availability, result.get());
        assertEquals(availability.getDate(), date);
        assertEquals(availability.getStartTime(), startTime);
        Mockito.verify(officeDoctorAvailabilityEntityMapper).mapFromEntityWithOffice(availabilityEntity);
    }

    @Test
    public void testNewSave() {
        // given
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();

        // when
        officeDoctorAvailabilityRepository.newSave(availability);

        // then
        verify(officeDoctorAvailabilityJpaRepository).save(officeDoctorAvailabilityEntityMapper.mapToEntityWithOfficeNoId(availability));
    }
}
