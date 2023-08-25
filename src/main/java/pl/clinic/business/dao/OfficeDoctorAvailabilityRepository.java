package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.repository.jpa.OfficeDoctorAvailabilityJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.OfficeDoctorAvailabilityEntityMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    public List<OfficeDoctorAvailability> findAvailableHoursByOfficeId(Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByOfficeAndAvailabilityStatusIsTrue(officeId).stream()
                .map(entity->officeDoctorAvailabilityEntityMapper.mapFromEntity(entity))
                .toList();
    }

    public void setOfficeAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {

    }
}
