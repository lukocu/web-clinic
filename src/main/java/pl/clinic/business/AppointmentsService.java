package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentsRepository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private AppointmentsRepository appointmentsRepository;
    private AppointmentStatusService appointmentStatusService;

    @Transactional
    public void createScheduledAppointment(OfficeDoctorAvailability officeDoctorAvailability) {
        Appointments appointment = Appointments.builder()
                .probableStartTime(OffsetDateTime.of(
                        officeDoctorAvailability.getDate(),
                        officeDoctorAvailability.getStartTime(),
                        ZoneOffset.UTC))
                .appointmentTakenDate(LocalDate.now())
                .office(officeDoctorAvailability.getOffice()) // Przypisanie biura
                .appointmentStatus(createAppointmentStatus()) // Tworzenie statusu rezerwacji
                .build();

        appointmentsRepository.save(appointment);
    }

    private AppointmentStatus createAppointmentStatus() {
        return appointmentStatusService.createAppointmentStatus();
    }


}
