package pl.clinic.domain;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "prescriptionId")
@ToString(of = {"prescriptionId", "prescriptionDate", "prescriptionDateEnd", "prescriptionAvailable"})
@Builder
public class Prescriptions {

    Integer prescriptionId;

    OffsetDateTime prescriptionDate;

    OffsetDateTime prescriptionDateEnd;

    OffsetDateTime prescriptionAvailable;

    Set<Medications> medications;
}
