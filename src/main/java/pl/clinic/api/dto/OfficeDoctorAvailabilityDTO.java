package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDoctorAvailabilityDTO {


    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean availabilityStatus;
    private Integer officeId; // Może być ID biura, z którego pochodzi dostępność

    // Getters and setters (możesz użyć Lomboka do ich generowania)

}
