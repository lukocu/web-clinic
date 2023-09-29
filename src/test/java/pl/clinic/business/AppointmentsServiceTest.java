package pl.clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.AppointmentsRepository;
import pl.clinic.domain.*;
import pl.clinic.util.services.DomainFixtures;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentsServiceTest {
    @InjectMocks
    private AppointmentsService appointmentsService;
    @Mock
    private AppointmentsRepository appointmentsRepository;

    @Mock
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;


    @Test
    public void testCreateScheduledAppointment() {
        // given
        Patients patient = DomainFixtures.patient1();
        OfficeDoctorAvailability availability = DomainFixtures.availability1();

        //when
        doNothing().when(appointmentsRepository).save(any(Appointments.class));

        appointmentsService.createScheduledAppointment(availability, patient);

        // then
        verify(appointmentsRepository, times(1)).save(any(Appointments.class));

    }

    @Test
    public void testFindAppointmentsByPatientId() {
        // given
        Patients patient = DomainFixtures.patient1()
                .withAppointments(
                        Set.of(DomainFixtures.appointment2(), DomainFixtures.appointment1()));

        // when
        when(appointmentsRepository.findAppointmentsByPatientIdWithAllFields(patient.getPatientId()))
                .thenReturn(List.of(DomainFixtures.appointment1(), DomainFixtures.appointment2()));


        List<Appointments> appointments = appointmentsService.findAppointmentsByPatientId(patient.getPatientId());

        // then
        Assertions.assertEquals(2, appointments.size());
        Assertions.assertTrue(appointments.contains(DomainFixtures.appointment1()));
        Assertions.assertTrue(appointments.contains(DomainFixtures.appointment2()));
    }

    @Test
    public void testGetCurrentAppointmentWithOffice() {
        // given
        LocalDate date = LocalDate.of(2023, 9, 25);
        LocalTime startTime = LocalTime.of(10, 0);
        Office office = DomainFixtures.office1();

        // when
        OffsetDateTime offsetDateTime = OffsetDateTime.of(date, startTime, ZoneOffset.UTC);
        Appointments expectedAppointment = DomainFixtures.appointment1()
                .withProbableStartTime(offsetDateTime)
                .withOffice(office);
        when(appointmentsRepository.findByProbableStartTimeWithOffice(offsetDateTime, office))
                .thenReturn(Optional.of(expectedAppointment));


        Appointments appointment = appointmentsService.getCurrentAppointmentWithOffice(date, startTime, office);

        // then
        Assertions.assertEquals(expectedAppointment, appointment);
    }

    @Test
    public void getCompletedAndCanceledAppointmentsTest() {
        //given
        Patients patient = DomainFixtures.patient1()
                .withAppointments(Set.of(DomainFixtures.appointment1(), DomainFixtures.appointment2()));
        Patients anotherPatient = DomainFixtures.patient2()
                .withAppointments(Set.of(DomainFixtures.appointment1().withAppointmentStatus(AppointmentStatus.builder()
                                .status(Status.Completed)
                                .build()),
                        DomainFixtures.appointment2().withAppointmentStatus(AppointmentStatus.builder()
                                .status(Status.Canceled)
                                .build())));

        when(appointmentsRepository.findCompletedAndCanceledByPatient(patient.getPatientId()))
                .thenReturn(Collections.emptyList());


        when(appointmentsRepository.findCompletedAndCanceledByPatient(anotherPatient.getPatientId()))
                .thenReturn(anotherPatient.getAppointments().stream()
                        .toList());

        //when
        List<Appointments> completedAndCanceledAppointments =
                appointmentsService.getCompletedAndCanceledAppointments(patient.getPatientId());

        List<Appointments> completedAndCanceledAppointments2 =
                appointmentsService.getCompletedAndCanceledAppointments(anotherPatient.getPatientId());

        //then
        Assertions.assertEquals(0, completedAndCanceledAppointments.size());
        Assertions.assertEquals(2, completedAndCanceledAppointments2.size());

    }

    @Test
    public void appointmentCanceledTest() {
        //given
        OfficeDoctorAvailability availability = DomainFixtures.availability1()
                .withAvailabilityStatus(false)
                .withOffice(DomainFixtures.office1());

        OffsetDateTime offsetDateTime = OffsetDateTime.of(availability.getDate(), availability.getStartTime(), ZoneOffset.UTC);


        Appointments appointment = DomainFixtures.appointment1()
                .withProbableStartTime(offsetDateTime)
                .withOffice(availability.getOffice());

        when(appointmentsRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));


        doAnswer(invocation -> {

            OfficeDoctorAvailability availabilityArgument = invocation.getArgument(0);

            OfficeDoctorAvailability newStatus = availabilityArgument.withAvailabilityStatus(true);

            Assertions.assertTrue(newStatus.getAvailabilityStatus());

            return null;
        }).when(officeDoctorAvailabilityService).setAvailable(any(OfficeDoctorAvailability.class));


        officeDoctorAvailabilityService.setAvailable(availability);
        appointmentsService.appointmentCanceled(appointment.getAppointmentId());
        Optional<Appointments> result = appointmentsRepository.findById(appointment.getAppointmentId());



        Assertions.assertEquals(appointment.getAppointmentId(), result.get().getAppointmentId());
        Assertions.assertEquals(appointment.getProbableStartTime(), result.get().getProbableStartTime());


    }

}