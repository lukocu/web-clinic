package pl.clinic.business;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentsRepository;
import pl.clinic.domain.*;
import pl.clinic.domain.exception.NotFoundException;

import java.time.*;
import java.util.List;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    @Lazy
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;

    @Autowired
    private OfficeService officeService;


    @Transactional
    public void createScheduledAppointment(OfficeDoctorAvailability officeDoctorAvailability, Patients patient) {

      //  Office savedOffice = officeService.save(officeDoctorAvailability.getOffice());


        Appointments appointment = Appointments.builder()
                .probableStartTime(OffsetDateTime.of(
                        officeDoctorAvailability.getDate(),
                        officeDoctorAvailability.getStartTime(),
                        ZoneOffset.UTC))
                .appointmentTakenDate(LocalDate.now())
                .office(officeDoctorAvailability.getOffice())
                .appointmentStatus(AppointmentStatus.builder()
                        .appointmentStatusId(1)
                        .status(Status.Scheduled)
                        .build())
                .patient(patient)
                .build();
        appointmentsRepository.save(appointment);
    }

    @Transactional
    public List<Appointments> findAppointmentsByPatientId(Integer patientId) {
        return appointmentsRepository.findAppointmentsByPatientIdWithAllFields(patientId);
    }


    @Transactional
    public Appointments getCurrentAppointmentWithOffice(LocalDate date, LocalTime startTime, Office office) {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(date, startTime, ZoneOffset.UTC);
        return appointmentsRepository.findByProbableStartTimeWithOffice(offsetDateTime, office)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));
    }

    @Transactional
    public void updateStatus(Integer appointmentId, OfficeDoctorAvailability visit) {
        Appointments currentAppointment = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));


        Appointments updatedAppointment = currentAppointment
                .withActualEndTime(OffsetDateTime.of(
                        LocalDateTime.of(visit.getDate(), visit.getEndTime()), ZoneOffset.UTC))
                .withAppointmentStatus(AppointmentStatus.builder()
                        .appointmentStatusId(2)
                        .status(Status.Completed)
                        .build());

        appointmentsRepository.save(updatedAppointment);
    }

    public List<Appointments> getCompletedAndCanceledAppointments(Integer patientId) {
        return appointmentsRepository.findCompletedAndCanceledByPatient(patientId);
    }

    @Transactional
    public void appointmentCanceled(Integer appointmentId) {
        Appointments currentAppointment = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        Appointments appointmentWithNewStatus = currentAppointment.withAppointmentStatus(AppointmentStatus.builder()
                .appointmentStatusId(3)
                .status(Status.Canceled)
                .build());
        OfficeDoctorAvailability currentOfficeAvailability =
                officeDoctorAvailabilityService.getOfficeAvailabilityByStartTimeAndEndTime(
                        appointmentWithNewStatus.getProbableStartTime(),
                        appointmentWithNewStatus.getOffice());

        officeDoctorAvailabilityService.setAvailable(currentOfficeAvailability);


        appointmentsRepository.save(appointmentWithNewStatus);

    }
}
