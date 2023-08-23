package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"officeAvailabilityId"})
@ToString(of = {"officeAvailabilityId", "date", "startTime", "endTime", "availabilityStatus", "office"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "office_doctor_availability")
public class OfficeDoctorAvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_availability_id")
    private Integer officeAvailabilityId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "availability_status")
    private Boolean availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private OfficeEntity office;
}
