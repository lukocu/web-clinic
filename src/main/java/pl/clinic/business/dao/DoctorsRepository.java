package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.repository.jpa.DoctorsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.DoctorsEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DoctorsRepository {

    private DoctorsJpaRepository doctorsJpaRepository;
    private DoctorsEntityMapper doctorsEntityMapper;

    public Optional<Doctors> findByNameAndSurname(String name, String surname) {
        return doctorsJpaRepository.findByNameAndSurname(name, surname)
                .map(entity -> doctorsEntityMapper.mapFromEntity(entity));
    }

    public Optional<Doctors> findByPesel(String pesel) {
        return doctorsJpaRepository.findByPesel(pesel)
                .map(entity -> doctorsEntityMapper.mapFromEntity(entity));
    }
}
