package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"diseaseId"})
@ToString(of = {"diseaseId", "diseaseName", "diseaseDescription"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diseases")
public class DiseasesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diseases_seq")
    @SequenceGenerator(name = "diseases_seq", sequenceName = "diseases_seq", allocationSize = 1)
    @Column(name = "disease_id")
    private Integer diseaseId;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "disease_description")
    private String diseaseDescription;

    @ManyToMany(mappedBy = "diseases")
    private Set<PatientCardEntity> patientCards;

}
