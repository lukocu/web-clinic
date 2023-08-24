package pl.clinic.api.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class AppointmentsRepository  {

    private final AppointmentsJpaRepository appointmentsJpaRepository;
    private final AppointmentsEntityMapper appointmentsEntityMapper;


    public List<Appointments> findByOfficeDoctorAvailabilityDate(LocalDate date) {
        return appointmentsJpaRepository.findByOfficeDoctorAvailabilityDate(date).stream()
                .map(appointmentsEntityMapper::mapFromEntity)
                .toList();
    }
}
