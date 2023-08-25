package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.repository.jpa.AppointmentsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.AppointmentsEntityMapper;

@Repository
@AllArgsConstructor
public class AppointmentsRepository {

    private final AppointmentsJpaRepository appointmentsJpaRepository;
    private final AppointmentsEntityMapper appointmentsEntityMapper;

    public void save(Appointments appointment) {
        appointmentsJpaRepository.save(appointmentsEntityMapper.mapToEntity(appointment));
    }


}
