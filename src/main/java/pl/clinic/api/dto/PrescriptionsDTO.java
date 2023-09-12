package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.clinic.domain.Medications;

import java.time.OffsetDateTime;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionsDTO {
    Integer prescriptionId;

    OffsetDateTime prescriptionDate;

    OffsetDateTime prescriptionDateEnd;

    OffsetDateTime prescriptionAvailable;

    Set<Medications> medications;
}
