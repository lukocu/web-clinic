package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.DomainFixtures;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OfficeDoctorAvailabilityServiceTest {

    @InjectMocks
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;

    @Mock
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;

    @Test
    public void GetOfficeAvailabilityByStartTimeAndEndTimeTest() {
        // Given
        Office office = DomainFixtures.office1();


        LocalDate date = LocalDate.of(2023, 9, 25);
        LocalTime startTime = LocalTime.of(9, 0);
        OffsetDateTime probableStartTime = OffsetDateTime.of(date,
                startTime,
                OffsetDateTime.now().getOffset());

        OfficeDoctorAvailability expectedAvailability = DomainFixtures.availability1()
                .withOffice(office)
                .withDate(date)
                .withStartTime(startTime)
                .withEndTime(startTime.plusMinutes(30));


        when(officeDoctorAvailabilityRepository.findByDateAndTime(eq(date), eq(startTime), eq(office.getOfficeId())))
                .thenReturn(Optional.of(expectedAvailability));

        // When
        OfficeDoctorAvailability resultAvailability =
                officeDoctorAvailabilityService.getOfficeAvailabilityByStartTimeAndEndTime(probableStartTime, office);

        // Then
        assertNotNull(resultAvailability);
        assertEquals(expectedAvailability.getOfficeAvailabilityId(), resultAvailability.getOfficeAvailabilityId());
        assertEquals(expectedAvailability.getOffice(), resultAvailability.getOffice());
        assertEquals(expectedAvailability.getDate(), resultAvailability.getDate());
        assertEquals(expectedAvailability.getStartTime(), resultAvailability.getStartTime());
        assertEquals(expectedAvailability.getEndTime(), resultAvailability.getEndTime());

        verify(officeDoctorAvailabilityRepository, times(1))
                .findByDateAndTime(eq(date), eq(startTime), eq(office.getOfficeId()));
    }

    @Test
    public void GetOfficeAvailabilityByStartTimeAndEndTimeNotFoundTest() {
        // Given
        Office office = DomainFixtures.office1();


        LocalDate date = LocalDate.of(2023, 9, 25);
        LocalTime startTime = LocalTime.of(9, 0);
        OffsetDateTime probableStartTime = OffsetDateTime.of(date, startTime, OffsetDateTime.now().getOffset());



        when(officeDoctorAvailabilityRepository.findByDateAndTime(eq(date), eq(startTime), eq(office.getOfficeId())))
                .thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class,
                () -> officeDoctorAvailabilityService.getOfficeAvailabilityByStartTimeAndEndTime(probableStartTime, office));
        verify(officeDoctorAvailabilityRepository, times(1))
                .findByDateAndTime(eq(date), eq(startTime), eq(office.getOfficeId()));
    }
}
