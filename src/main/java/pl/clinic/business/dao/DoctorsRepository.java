package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;
import pl.clinic.infrastructure.database.repository.jpa.DoctorsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.DoctorsEntityMapper;

import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class DoctorsRepository {

    private DoctorsJpaRepository doctorsJpaRepository;
    private DoctorsEntityMapper doctorsEntityMapper;
    public Doctors findByNameAndSurname(String name, String surname) {
        return doctorsEntityMapper.mapFromEntity(doctorsJpaRepository.findByNameAndSurname(name, surname));
    }
}
