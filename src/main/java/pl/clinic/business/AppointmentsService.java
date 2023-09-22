package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentsRepository;
import pl.clinic.domain.*;
import pl.clinic.domain.exception.NotFoundException;

import java.time.*;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private AppointmentsRepository appointmentsRepository;


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
                        .appointmentStatusId(1)
                        .status(Status.Scheduled)
                        .build()) // Tworzenie statusu rezerwacji
                .patient(patient)
                .build();

        appointmentsRepository.save(appointment);
    }

    @Transactional
    public List<Appointments> findAppointmentsByPatientId(Integer patientId) {
        return appointmentsRepository.findAppointmentsByPatientIdWithAllFields(patientId);
    }



    @Transactional
    public Appointments getCurrentAppointementWithOffice(LocalDate date, LocalTime startTime, Office office) {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(date, startTime, ZoneOffset.UTC);
        return appointmentsRepository.findByProbableStartTimeWithOffice(offsetDateTime, office)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));
    }

    @Transactional
    public void UpdateStatus(Integer appointmentId, OfficeDoctorAvailability visit) {
        Appointments currentAppointment = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));


            Appointments updatedAppointment = currentAppointment
                    .withActualEndTime(OffsetDateTime.of(
                            LocalDateTime.of(visit.getDate(),visit.getEndTime()),ZoneOffset.UTC))
                    .withAppointmentStatus(AppointmentStatus.builder()
                    .appointmentStatusId(2)
                    .status(Status.Completed)
                    .build());

            appointmentsRepository.save(updatedAppointment);
        }

        public List<Appointments> getCompletedAndCanceledAppointments (Integer patientId){
            return appointmentsRepository.findCompletedAndCanceledByPatient(patientId);
        }
    }
