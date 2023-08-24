package pl.clinic.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "specializationId")
@ToString(of = {"specializationId", "specializationName"})
@Builder
public class Specialization {

    Integer specializationId;

    String specializationName;

    Set<Doctors> doctors;


}