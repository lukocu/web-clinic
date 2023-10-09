package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.domain.*;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentsEntityMapperTest {

    @InjectMocks
    private AppointmentsEntityMapperImpl appointmentsEntityMapperImpl;

    @Test
    public void testMapFromEntityWithFields() {

        AppointmentsEntity entity = EntityFixtures.appointment1();

        Appointments appointments = appointmentsEntityMapperImpl.mapFromEntity(entity);

        assertInstanceOf(Appointments.class, appointments);
        assertInstanceOf(AppointmentsEntity.class, entity);

        assertEquals(entity.getAppointmentId(), appointments.getAppointmentId());
        assertEquals(entity.getProbableStartTime(), appointments.getProbableStartTime());
        assertEquals(entity.getActualEndTime(), appointments.getActualEndTime());
        assertEquals(entity.getAppointmentTakenDate(), appointments.getAppointmentTakenDate());

        assertEquals(entity.getPatient().getPatientId(), appointments.getPatient().getPatientId());
        assertEquals(entity.getPatient().getName(), appointments.getPatient().getName());
        assertInstanceOf(Patients.class, appointments.getPatient());

        assertEquals(entity.getOffice().getOfficeId(), appointments.getOffice().getOfficeId());
        assertEquals(entity.getOffice().getFirstConsultationFee(), appointments.getOffice().getFirstConsultationFee());
        assertInstanceOf(Office.class, appointments.getOffice());

        assertEquals(entity.getAppointmentStatus().getStatus(), appointments.getAppointmentStatus().getStatus());
        assertInstanceOf(AppointmentStatus.class, appointments.getAppointmentStatus());

    }

    @Test
    public void testMapToEntityWithFields() {

        Appointments appointments = DomainData.appointment1();
        Office office = appointments.getOffice().withDoctor(Doctors.builder()
                .doctorId(10)
                .name("John")
                .surname("Due")
                .phone("123-456-789")
                .pesel("84051212345")
                .build());

        Appointments correctAppointment = appointments.withOffice(office);

        AppointmentsEntity expectedEntity = EntityFixtures.appointment1();



        AppointmentsEntity entity = appointmentsEntityMapperImpl.mapToEntity(correctAppointment);


        assertEquals(expectedEntity.getAppointmentId(), entity.getAppointmentId());
        assertEquals(expectedEntity.getProbableStartTime(), entity.getProbableStartTime());
        assertEquals(expectedEntity.getActualEndTime(), entity.getActualEndTime());
        assertEquals(expectedEntity.getAppointmentTakenDate(), entity.getAppointmentTakenDate());

        assertEquals(expectedEntity.getPatient().getPatientId(), entity.getPatient().getPatientId());
        assertEquals(expectedEntity.getPatient().getName(), entity.getPatient().getName());


        assertEquals(expectedEntity.getOffice().getFirstConsultationFee(), entity.getOffice().getFirstConsultationFee());

        assertEquals(expectedEntity.getAppointmentStatus().getStatus(), entity.getAppointmentStatus().getStatus());
    }



}

