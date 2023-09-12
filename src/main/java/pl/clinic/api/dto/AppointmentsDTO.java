package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentsDTO {
    Integer appointmentId;
    OffsetDateTime probableStartTime;
    OffsetDateTime actualEndTime;
    LocalDate appointmentTakenDate;
    PatientsDTO patient;
    OfficeDTO office;
    AppointmentStatusDTO appointmentStatus;
}
