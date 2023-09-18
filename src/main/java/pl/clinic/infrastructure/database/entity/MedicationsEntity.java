package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "medicationId")
@ToString(of = {"medicationName", "dosage", "frequency", "duration"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medications")
public class MedicationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Integer medicationId;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "duration")
    private String duration;

    @ManyToMany(mappedBy = "medications")
    private Set<PrescriptionsEntity> prescriptions;
}
