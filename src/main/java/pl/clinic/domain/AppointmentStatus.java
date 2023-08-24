package pl.clinic.domain;

import lombok.*;

@With
@Value
@EqualsAndHashCode(of = "appointmentStatusId")
@ToString(of = {"appointmentStatusId", "status"})
@Builder
public class AppointmentStatus {

    Integer appointmentStatusId;

    Status status;

    Appointments appointments;
}
