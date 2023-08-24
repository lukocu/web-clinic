package pl.clinic.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = {"diseaseId"})
@ToString(of = {"diseaseId", "diseaseName", "diseaseDescription"})
@Builder
public class Diseases {

    Integer diseaseId;

    String diseaseName;

    String diseaseDescription;

    Set<PatientCard> patientCards;

}
