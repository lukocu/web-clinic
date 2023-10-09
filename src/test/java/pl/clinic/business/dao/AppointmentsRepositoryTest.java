package pl.clinic.business.dao;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AppointmentsRepositoryTest {

    @Mock
    private AppointmentsJpaRepository appointmentsJpaRepository;

    @Mock
    private AppointmentsEntityMapper appointmentsEntityMapper;

    @InjectMocks
    private AppointmentsRepository appointmentsRepository;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testSave() {
        // Tworzymy przykładowe dane
        Appointments appointment = DomainData.appointment1().withOffice(DomainData.officeForDoctor2());
        AppointmentsEntity appointmentsEntity = EntityFixtures.appointment1();

        // Mockowanie zachowań
        when(appointmentsJpaRepository.save(any())).thenReturn(appointmentsEntity);

        when(appointmentsEntityMapper.mapToEntity(appointment)).thenReturn(appointmentsEntity);

        // Wywołanie metody, którą testujemy
        appointmentsRepository.save(appointment);

        // Sprawdzamy, czy metoda save na mocku appointmentsJpaRepository została wywołana z odpowiednim obiektem
        ArgumentCaptor<AppointmentsEntity> captor = ArgumentCaptor.forClass(AppointmentsEntity.class);
        verify(appointmentsJpaRepository).save(captor.capture());

        // Porównujemy, czy obiekt przekazany do save jest tym, który oczekujemy
        AppointmentsEntity savedEntity = captor.getValue();
        assertThat(savedEntity).isEqualTo(appointmentsEntity);
    }


    @Test
    public void testFindAppointmentsByPatientIdWithAllFields() {
        // Arrange
        Integer patientId = 1;


        Appointments appointment=DomainData.appointment1();
        AppointmentsEntity appointmentEntity= EntityFixtures.appointment1();
        List<AppointmentsEntity> appointmentsEntities= Collections.singletonList(appointmentEntity);

        when(appointmentsJpaRepository.findByPatientIdWithScheduled(patientId)).thenReturn(appointmentsEntities);
        when(appointmentsEntityMapper.mapFromEntity(any(AppointmentsEntity.class))).thenReturn(appointment);

        List<Appointments> result  = appointmentsRepository.findAppointmentsByPatientIdWithAllFields(1);


        assertEquals(1, result.size());
        assertEquals(appointment, result.get(0));
        Mockito.verify(appointmentsEntityMapper).mapFromEntity(appointmentEntity);

 }
    @Test
    public void testFindCompletedAndCanceledByPatient() {
        // Arrange
        Integer patientId = 1;

        Appointments appointment = DomainData.appointment1();
        AppointmentsEntity appointmentEntity = EntityFixtures.appointment1();
        List<AppointmentsEntity> appointmentsEntities = Collections.singletonList(appointmentEntity);

        when(appointmentsJpaRepository.findCompletedAndCanceledByPatientId(patientId)).thenReturn(appointmentsEntities);
        when(appointmentsEntityMapper.mapFromEntity(any(AppointmentsEntity.class))).thenReturn(appointment);

        // Act
        List<Appointments> result = appointmentsRepository.findCompletedAndCanceledByPatient(patientId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(appointment, result.get(0));
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer appointmentId = 1;

        Appointments appointment = DomainData.appointment1();
        AppointmentsEntity appointmentEntity = EntityFixtures.appointment1();

        when(appointmentsJpaRepository.findById(appointmentId)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentsEntityMapper.mapFromEntity(any(AppointmentsEntity.class))).thenReturn(appointment);

        // Act
        Optional<Appointments> result = appointmentsRepository.findById(appointmentId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
    }

}
