package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentsJpaRepository extends JpaRepository<AppointmentsEntity,Integer> {


}
