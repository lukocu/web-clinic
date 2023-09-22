package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.clinic.domain.Status;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "appointmentStatusId")
@ToString(of = {"appointmentStatusId", "status"})
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

    @OneToMany(mappedBy = "appointmentStatus", cascade = CascadeType.ALL)
    private List<AppointmentsEntity> appointments;
}

