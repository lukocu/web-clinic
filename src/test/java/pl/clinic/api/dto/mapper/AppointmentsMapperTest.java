package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.domain.Appointments;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentsMapperTest {

    @Test
    public void testMapToDto() {

        Appointments appointments = DomainData.appointment1();


        AppointmentsDTO result = AppointmentsMapper.INSTANCE.mapToDto(appointments);


        assertEquals(appointments.getAppointmentId(), result.getAppointmentId());
        assertEquals(appointments.getProbableStartTime(), result.getProbableStartTime());
        assertEquals(appointments.getActualEndTime(), result.getActualEndTime());
        assertEquals(appointments.getAppointmentTakenDate(), result.getAppointmentTakenDate());
        assertNotNull(result.getPatient());
        assertNotNull(result.getOffice());
        assertNotNull(result.getAppointmentStatus());
    }

    @Test
    public void testMapToDtoWithoutOffice() {

        Appointments appointments = DomainData.appointment1();


        AppointmentsDTO result = AppointmentsMapper.INSTANCE.mapToDtoWithoutOffice(appointments);


        assertEquals(appointments.getAppointmentId(), result.getAppointmentId());
        assertEquals(appointments.getProbableStartTime(), result.getProbableStartTime());
        assertEquals(appointments.getActualEndTime(), result.getActualEndTime());
        assertEquals(appointments.getAppointmentTakenDate(), result.getAppointmentTakenDate());
        assertNotNull(result.getPatient());
        assertNull(result.getOffice());
        assertNotNull(result.getAppointmentStatus());
    }


    @Test
    public void testMapFromDtoWithoutOffice() {

        AppointmentsDTO appointmentsDTO = DTOFixtures.appointment1();


        Appointments result = AppointmentsMapper.INSTANCE.mapFromDtoWithoutOffice(appointmentsDTO);


        assertEquals(appointmentsDTO.getAppointmentId(), result.getAppointmentId());
        assertEquals(appointmentsDTO.getProbableStartTime(), result.getProbableStartTime());
        assertEquals(appointmentsDTO.getActualEndTime(), result.getActualEndTime());
        assertEquals(appointmentsDTO.getAppointmentTakenDate(), result.getAppointmentTakenDate());
        assertNotNull(result.getPatient());
        assertNull(result.getOffice());
        assertNotNull(result.getAppointmentStatus());
    }
}
