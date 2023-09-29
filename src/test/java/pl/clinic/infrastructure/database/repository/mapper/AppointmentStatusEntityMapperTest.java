package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AppointmentStatusEntityMapperTest {

    @Test
    public void testMapFromEntity() {

        AppointmentStatusEntity entity = EntityFixtures.scheduledStatusEntity();

        AppointmentStatus status = AppointmentStatusEntityMapper.INSTANCE.mapFromEntity(entity);

        assertEquals(entity.getAppointmentStatusId(), status.getAppointmentStatusId());
        assertEquals(entity.getStatus(), status.getStatus());
        assertInstanceOf(AppointmentStatus.class, status);
    }

    @Test
    public void testMapToEntity() {

        AppointmentStatus status = DomainData.scheduledStatus();

        AppointmentStatusEntity entity = AppointmentStatusEntityMapper.INSTANCE.mapToEntity(status);

        assertEquals(status.getAppointmentStatusId(), entity.getAppointmentStatusId());
        assertEquals(status.getStatus(), entity.getStatus());
    }
}
