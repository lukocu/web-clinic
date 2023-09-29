package pl.clinic.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DiseasesDTO {


    String diseaseName;

    String diseaseDescription;
}
