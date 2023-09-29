package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.AppointmentStatusDTO;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentStatusMapperTest {

    @Test
    public void testMapToDto() {

        AppointmentStatus appointmentStatus = DomainData.scheduledStatus();


        AppointmentStatusDTO result = AppointmentStatusMapper.INSTANCE.mapToDto(appointmentStatus);


        assertEquals(appointmentStatus.getAppointmentStatusId(), result.getAppointmentStatusId());
        assertEquals(String.valueOf(appointmentStatus.getStatus()), result.getStatus());
    }

    @Test
    public void testMapFromDto() {

        AppointmentStatusDTO appointmentStatusDTO = DTOFixtures.completedStatus();

        // Map from DTO using mapFromDto
        AppointmentStatus result = AppointmentStatusMapper.INSTANCE.mapFromDto(appointmentStatusDTO);

        // Assert that the result has the expected values
        assertEquals(appointmentStatusDTO.getAppointmentStatusId(), result.getAppointmentStatusId());
        assertEquals(Status.valueOf(appointmentStatusDTO.getStatus()), result.getStatus());
    }


}
