package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDTO {

    private Integer diseaseId;
    private String diseaseName;
    private String diseaseDescription;

    // Getters and setters (możesz użyć lomboka do ich generowania)

}
