package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDTO {

    private Integer specializationId;
    private String specializationName;

    Set<DoctorDTO> doctors;

}
