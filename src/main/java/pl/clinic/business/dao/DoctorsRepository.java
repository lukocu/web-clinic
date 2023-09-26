package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.repository.jpa.DoctorsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.DoctorsEntityMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DoctorsRepository {

    private DoctorsJpaRepository doctorsJpaRepository;
    private DoctorsEntityMapper doctorsEntityMapper;



    public List<Doctors> findDoctorsAndOffice() {
        return doctorsJpaRepository.findDoctorsAndOffice().stream()
                .map(entity -> doctorsEntityMapper.mapFromEntityWithSpecializationsAndOffices(entity))
                .toList();
    }

    public Optional<Doctors> findByUserId(Integer userId) {
        return doctorsJpaRepository.findByUserId(userId)
                .map(entity-> doctorsEntityMapper.mapFromEntityWithAllFields(entity));
    }
}
