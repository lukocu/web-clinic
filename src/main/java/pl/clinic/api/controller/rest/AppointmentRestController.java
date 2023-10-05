package pl.clinic.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.AppointmentsMapper;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.api.dto.mapper.OfficeMapper;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.OfficeService;
import pl.clinic.business.PatientsService;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.Patients;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
public class AppointmentRestController {

    private AppointmentsService appointmentsService;
    private AppointmentsMapper appointmentsMapper;
    private OfficeService officeService;
    private PatientsService patientsService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;


    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<AppointmentsDTO>> getAppointmentsForPatient(@PathVariable Integer patientId) {
        List<AppointmentsDTO> appointments = appointmentsService.findAppointmentsByPatientId(patientId).stream()
                .map(appointments1 -> appointmentsMapper.mapToDto(appointments1))
                .toList();

        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/date/{officeId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentsDTO> getCurrentAppointment(
            @RequestBody String date,
            @RequestBody String startTime,
            @PathVariable Integer officeId) {

        LocalDate parseDate = LocalDate.parse(date);
        LocalTime parseTime = LocalTime.parse(startTime);
        Office office = officeService.getOffice(officeId);

        Appointments currentAppointment
                = appointmentsService.getCurrentAppointmentWithOffice(parseDate, parseTime, office);

        return ResponseEntity.ok(appointmentsMapper.mapToDto(currentAppointment));
    }

    @PostMapping("/schedule/{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> scheduleAppointment(
            @RequestBody OfficeDoctorAvailability officeDoctorAvailability,
            @PathVariable Integer patientId) {
        Patients patient = patientsService.getPatient(patientId);

        appointmentsService.createScheduledAppointment(officeDoctorAvailability, patient);

        return ResponseEntity.ok("Appointment scheduled successfully");
    }

    @PutMapping("/update-status/{appointmentId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<String> updateAppointmentStatus(
            @PathVariable Integer appointmentId,
            @RequestBody OfficeDoctorAvailabilityDTO visitDTO) {

        OfficeDoctorAvailability officeDoctorAvailability =
                officeDoctorAvailabilityMapper.mapFromDtoWithOffice(visitDTO);
        appointmentsService.updateStatus(appointmentId, officeDoctorAvailability);

        return ResponseEntity.ok("Appointment status updated successfully");
    }


}
