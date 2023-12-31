package pl.clinic.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "appointmentId")
@ToString(of = {"appointmentId", "probableStartTime", "actualEndTime", "appointmentTakenDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class AppointmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_seq")
    @SequenceGenerator(name = "appointments_seq", sequenceName = "appointments_seq", allocationSize = 1)
    @Column(name = "appointment_id")
    private Integer appointmentId;

    @Column(name = "probable_start_time")
    private OffsetDateTime probableStartTime;

    @Column(name = "actual_end_time")
    private OffsetDateTime actualEndTime;

    @Column(name = "appointment_taken_date")
    private LocalDate appointmentTakenDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientsEntity patient;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "office_id")
    private OfficeEntity office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_status_id")
    private AppointmentStatusEntity appointmentStatus;
}
