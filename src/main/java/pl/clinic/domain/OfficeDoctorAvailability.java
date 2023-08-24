package pl.clinic.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@With
@Value
@EqualsAndHashCode(of = {"officeAvailabilityId"})
@ToString(of = {"officeAvailabilityId", "date", "startTime", "endTime", "availabilityStatus", "office"})
@Builder
public class OfficeDoctorAvailability {

    Integer officeAvailabilityId;

    LocalDate date;

    LocalTime startTime;

    LocalTime endTime;

    Boolean availabilityStatus;

    Office office;
}
