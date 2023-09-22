package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "patientCardId")
@ToString(of = {"patientCardId", "diagnosisDate", "diagnosisNote"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_card")
public class PatientCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_card_id")
    private Integer patientCardId;

    @Column(name = "diagnosis_date")
    private OffsetDateTime diagnosisDate;

    @Column(name = "diagnosis_note")
    private String diagnosisNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientsEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorsEntity doctor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "patient_disease",
            joinColumns = @JoinColumn(name = "patient_card_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<DiseasesEntity> diseases;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    private PrescriptionsEntity prescription;

}
