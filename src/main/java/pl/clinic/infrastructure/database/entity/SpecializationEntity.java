package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "specializationId")
@ToString(of = {"specializationId", "specializationName"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialization")
public class SpecializationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialization_seq")
    @SequenceGenerator(name = "specialization_seq", sequenceName = "specialization_seq", allocationSize = 1)
    @Column(name = "specialization_id")
    private Integer specializationId;

    @Column(name = "specialization_name")
    private String specializationName;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<DoctorsEntity> doctors;


}