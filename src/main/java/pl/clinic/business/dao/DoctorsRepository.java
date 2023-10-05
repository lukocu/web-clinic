package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.repository.jpa.DoctorsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.DoctorsEntityMapper;

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
                .map(entity-> doctorsEntityMapper.mapFromEntityWithFields(entity));
    }

    public List<Doctors> findAll() {
        return doctorsJpaRepository.findAll().stream()
                .map(entity-> doctorsEntityMapper.mapFromEntityWithSpecializationsAndOffices(entity))
                .toList();
    }

    public Doctors save(Doctors doctor) {
        DoctorsEntity savedDoctor = doctorsJpaRepository.save(doctorsEntityMapper.mapToEntityNewDoctor(doctor));
        return doctorsEntityMapper.mapFromEntity(savedDoctor);
    }

    public Optional<Doctors> findById(Integer doctorId) {
        return doctorsJpaRepository.findByIdWithUser(doctorId)
                .map(entity-> doctorsEntityMapper.mapFromEntityWithAllFields(entity));
    }

    public void deleteById(Integer doctorId) {
        doctorsJpaRepository.deleteById(doctorId);
    }

    public void updateDoctor(Doctors updatedDoctor) {
         doctorsJpaRepository.save(
                doctorsEntityMapper.mapToEntityWithAllFields(updatedDoctor));

    }
}
