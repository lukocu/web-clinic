package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"diseaseId"})
@ToString(of = {"diseaseId","diseaseName","diseaseDescription"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diseases")
public class DiseasesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Integer diseaseId;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column( name = "disease_description")
    private String diseaseDescription;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "patient_disease",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_card_id")
    )
    private Set<PatientCardEntity> patientCards;

}
