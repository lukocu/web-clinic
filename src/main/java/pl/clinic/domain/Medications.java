package pl.clinic.domain;

import lombok.*;

@With
@Value
@EqualsAndHashCode(of = "medicationId")
@ToString(of = {"medicationName", "dosage", "frequency", "duration"})
@Builder
public final class Medications {

    private final Integer medicationId;

    private final String medicationName;

    private final String dosage;

    private final String frequency;

    private final String duration;


}
