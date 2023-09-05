package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDTO {

    private Integer officeId;
    private BigDecimal firstConsultationFee;
    private BigDecimal followupConsultationFee;
    private DoctorDTO doctor; // Zamiast ID lekarza, używamy obiektu DoctorDTO
    private Set<OfficeDoctorAvailabilityDTO> officeDoctorAvailability;
    private Set<AppointmentsDTO> appointment;

}
