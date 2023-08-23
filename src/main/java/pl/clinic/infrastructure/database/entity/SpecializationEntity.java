package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "specializationId")
@ToString(of = "specializationName")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialization")
public class SpecializationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private String specializationId;

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