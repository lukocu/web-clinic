package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentsRepository;
import pl.clinic.domain.*;
import pl.clinic.domain.exception.NotFoundException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private AppointmentsRepository appointmentsRepository;
    private AppointmentStatusService appointmentStatusService;

    @Transactional
    public void createScheduledAppointment(OfficeDoctorAvailability officeDoctorAvailability, Patients patient) {
        Appointments appointment = Appointments.builder()
                .probableStartTime(OffsetDateTime.of(
                        officeDoctorAvailability.getDate(),
                        officeDoctorAvailability.getStartTime(),
                        ZoneOffset.UTC))
                .appointmentTakenDate(LocalDate.now())
                .office(officeDoctorAvailability.getOffice()) // Przypisanie biura
                .appointmentStatus(AppointmentStatus.builder()
                        .status(Status.Scheduled)
                        .build()) // Tworzenie statusu rezerwacji
                .patient(patient)
                .build();

        appointmentsRepository.save(appointment);
    }

    @Transactional
    public List<Appointments> findAppointmentsByPatientId(Integer patientId) {
        return appointmentsRepository.findAppointmentsByPatientId(patientId);
    }
}
