package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "prescriptionId")
@ToString(of = {"prescriptionId", "prescriptionDate", "prescriptionDateEnd", "prescriptionAvailable"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescriptions")
public class PrescriptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescriptions_seq")
    @SequenceGenerator(name = "prescriptions_seq", sequenceName = "prescriptions_seq", allocationSize = 1)
    @Column(name = "prescription_id")
    private Integer prescriptionId;

    @Column(name = "prescription_date", nullable = false)
    private OffsetDateTime prescriptionDate;

    @Column(name = "prescription_date_end")
    private OffsetDateTime prescriptionDateEnd;

    @Column(name = "prescription_available")
    private Boolean prescriptionAvailable;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prescription_medications",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Set<MedicationsEntity> medications;
}
