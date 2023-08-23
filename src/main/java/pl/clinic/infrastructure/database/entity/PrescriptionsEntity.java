package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"prescriptionDate","prescriptionDateEnd","prescriptionAvailable"})
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescriptions")
public class PrescriptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private OffsetDateTime prescriptionId;

    @Column(name = "prescription_date")
    private OffsetDateTime prescriptionDate;

    @Column(name = "prescription_date_end")
    private OffsetDateTime prescriptionDateEnd;

    @Column(name = "prescription_available")
    private OffsetDateTime prescriptionAvailable;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<MedicationsEntity> medications;
}
