package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {

    private Integer medicationId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private Duration duration;

    // Getters and setters (możesz użyć Lomboka do ich generowania)

}
