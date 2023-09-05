package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentStatusDTO {

    private Integer appointmentStatusId;
    private String status;

    // Getters and setters (możesz użyć Lomboka do ich generowania)

}
