package pl.clinic.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.clinic.business.dao.AppointmentStatusRepository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.DomainData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AppointmentStatusServiceTest {

    private AppointmentStatusService appointmentStatusService;

    @Mock
    private AppointmentStatusRepository appointmentStatusRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentStatusService = new AppointmentStatusService(appointmentStatusRepository);
    }

    @Test
    public void testFindByStatus() {

        Status status = Status.Scheduled;
        AppointmentStatus appointmentStatus = DomainData.scheduledStatus();


        when(appointmentStatusRepository.findByStatus(status)).thenReturn(Optional.of(appointmentStatus));


        AppointmentStatus result = appointmentStatusService.findByStatus(status);


        assertEquals(appointmentStatus, result);
    }

    @Test
    public void testFindByStatusNotFound() {

        Status status = Status.Canceled;


        when(appointmentStatusRepository.findByStatus(status)).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> appointmentStatusService.findByStatus(status));
    }
}
