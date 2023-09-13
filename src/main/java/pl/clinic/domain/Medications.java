package pl.clinic.domain;

import lombok.*;

import java.time.Duration;

@With
@Value
@EqualsAndHashCode(of = "medicationId")
@ToString(of = {"medicationName", "dosage", "frequency", "duration"})
@Builder
public class Medications {

    Integer medicationId;

    String medicationName;

    String dosage;

    String frequency;

    String duration;


}
