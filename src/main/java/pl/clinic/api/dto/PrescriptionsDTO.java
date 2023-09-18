package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionsDTO {

    OffsetDateTime prescriptionDate;

    OffsetDateTime prescriptionDateEnd;

    Boolean prescriptionAvailable;

    Set<MedicationsDTO> medications;
}
