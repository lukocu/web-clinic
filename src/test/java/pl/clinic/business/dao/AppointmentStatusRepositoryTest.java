package pl.clinic.business.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentStatusJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentStatusEntityMapper;
import pl.clinic.util.DomainData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentStatusRepositoryTest {

    @Mock
    private AppointmentStatusJpaRepository appointmentStatusJpaRepository;

    @Mock
    private AppointmentStatusEntityMapper appointmentStatusEntityMapper;

    @InjectMocks
    private AppointmentStatusRepository appointmentStatusRepository;

    @Test
    public void testSave() {
        // given
        AppointmentStatus newStatus = DomainData.scheduledStatus();

        // when
        appointmentStatusRepository.save(newStatus);

        // then
        verify(appointmentStatusJpaRepository).save(appointmentStatusEntityMapper.mapToEntity(newStatus));
    }

    @Test
    public void testFindByStatus() {
        // given
        Status status = Status.Scheduled;


        AppointmentStatusEntity appointmentStatusEntity = new AppointmentStatusEntity();
        appointmentStatusEntity.setStatus(status);


        AppointmentStatus appointmentStatus = DomainData.scheduledStatus();


        when(appointmentStatusJpaRepository.findByStatus(status)).thenReturn(Optional.of(appointmentStatusEntity));
        when(appointmentStatusEntityMapper.mapFromEntity(any(AppointmentStatusEntity.class))).thenReturn(appointmentStatus);

        // when
        Optional<AppointmentStatus> result = appointmentStatusRepository.findByStatus(status);

        // then
        assertTrue(result.isPresent());
        assertEquals(appointmentStatus, result.get());
        Mockito.verify(appointmentStatusEntityMapper).mapFromEntity(appointmentStatusEntity);
    }
}
