package pl.clinic.domain;


import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@With
@Value
@EqualsAndHashCode(of = "appointmentId")
@ToString(of = {"appointmentId", "probableStartTime", "actualEndTime", "appointmentTakenDate"})
@Builder
public class Appointments {

    Integer appointmentId;

    OffsetDateTime probableStartTime;

    OffsetDateTime actualEndTime;

    LocalDate appointmentTakenDate;

    Patients patient;

    Office office;

    AppointmentStatus appointmentStatus;
}
