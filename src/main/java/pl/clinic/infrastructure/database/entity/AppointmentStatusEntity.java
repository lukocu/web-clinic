package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "status")
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment_status")
public class AppointmentStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_status_id")
    private Integer appointmentStatusId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "appointmentStatus")
    private AppointmentsEntity appointmentsEntity;
}
