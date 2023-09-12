package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.repository.jpa.OfficeDoctorAvailabilityJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.OfficeDoctorAvailabilityEntityMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OfficeDoctorAvailabilityRepository {

    private OfficeDoctorAvailabilityJpaRepository officeDoctorAvailabilityJpaRepository;
    private OfficeDoctorAvailabilityEntityMapper officeDoctorAvailabilityEntityMapper;

    public List<OfficeDoctorAvailability> findAvailableHoursForDoctor(String name, String surname) {
        return officeDoctorAvailabilityJpaRepository.findAvailableHoursForDoctor(name, surname).stream()
                .map(officeDoctorAvailabilityEntityMapper::mapFromEntity)
                .toList();
    }

    public void save(OfficeDoctorAvailability availability) {
        officeDoctorAvailabilityJpaRepository.save(officeDoctorAvailabilityEntityMapper.mapToEntity(availability));
    }

    public List<OfficeDoctorAvailability> findByOfficeAndAvailabilityStatusIsTrue(Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByOfficeIdAndAvailabilityStatusIsTrue(officeId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }

    public List<OfficeDoctorAvailability> findByOfficeAndAvailabilityStatusIsFalse(Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByOfficeIdAndAvailabilityStatusIsFalse(officeId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }



    public Optional<OfficeDoctorAvailability> findById(Integer officeAvailabilityId) {
        return officeDoctorAvailabilityJpaRepository.findById(officeAvailabilityId)
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity));
    }

}
