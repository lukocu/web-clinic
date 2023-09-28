package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.util.DomainFixtures;
import pl.clinic.util.EntityFixtures;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentsEntityMapperTest {
    @Mock
    PatientsEntityMapper patientsEntityMapper;
    @Mock
    OfficeEntityMapper officeEntityMapper;

    @Test
    public void testMapFromEntity() {
        // Przykładowa encja
        AppointmentsEntity entity = EntityFixtures.appointment1();


        // Konfiguracja zachowania atrap
        when(patientsEntityMapper.mapFromEntity(entity.getPatient())).thenReturn(/* tutaj oczekiwany obiekt Patient */);
        when(officeEntityMapper.mapFromEntityWithoutAppointments(entity.getOffice())).thenReturn(/* tutaj oczekiwany obiekt Office */);

        // Tworzenie mappera
        AppointmentsEntityMapper mapper = new AppointmentsEntityMapperImpl(patientsEntityMapper, officeEntityMapper);

        // Wywołanie metody mapującej
        Appointments appointments = mapper.mapFromEntity(entity);

        // Asercje
        assertEquals(entity.getAppointmentId(), appointments.getAppointmentId());
        assertEquals(entity.getProbableStartTime(), appointments.getProbableStartTime());
        assertEquals(entity.getActualEndTime(), appointments.getActualEndTime());
        assertEquals(entity.getAppointmentTakenDate(), appointments.getAppointmentTakenDate());
        // Tutaj można dodać asercje dla mapowanych obiektów Patient i Office
    }
    }
}
