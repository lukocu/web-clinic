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
                .map(officeDoctorAvailabilityEntityMapper::mapFromEntityWithOffice)
                .toList();
    }

    public void save(OfficeDoctorAvailability availability) {
        officeDoctorAvailabilityJpaRepository.save(officeDoctorAvailabilityEntityMapper.mapToEntityWithOffice(availability));
    }

    public List<OfficeDoctorAvailability> findByOfficeAndAvailabilityStatusIsTrue(Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByOfficeIdAndAvailabilityStatusIsTrue(officeId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }




    public Optional<OfficeDoctorAvailability> findById(Integer officeAvailabilityId) {
        return officeDoctorAvailabilityJpaRepository.findByIdAndOffice(officeAvailabilityId)
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOfficeFields(entity));
    }

    public void deleteById(Integer officeAvailabilityId) {
        officeDoctorAvailabilityJpaRepository.deleteByIdCustom(officeAvailabilityId);
    }



    public List<OfficeDoctorAvailability> findAvailableStatusIsFalseWithDoctorId(Integer doctorId) {
        return officeDoctorAvailabilityJpaRepository.findUnavailableStatusForDoctor(doctorId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }

    public List<OfficeDoctorAvailability> findByDateAndTimeRange(LocalDate date, LocalTime startTime, LocalTime endTime, Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByDateAndTimeRange(date, startTime, endTime, officeId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }

    public List<OfficeDoctorAvailability> findConflictingAppointments(LocalDate date,
                                                                      LocalTime startTime,
                                                                      LocalTime endTime, Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findConflictingAppointments(date, startTime, endTime, officeId).stream()
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity))
                .toList();
    }

    public Optional<OfficeDoctorAvailability> findByDateAndTime(LocalDate date,
                                                                LocalTime startTime,
                                                             Integer officeId) {
        return officeDoctorAvailabilityJpaRepository.findByDateAndTime(date, startTime, officeId)
                .map(entity -> officeDoctorAvailabilityEntityMapper.mapFromEntityWithOffice(entity));
    }


}
