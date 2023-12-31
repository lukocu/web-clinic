package pl.clinic.business;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.domain.exception.SlotNotAvailableException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OfficeDoctorAvailabilityService {

    @Autowired
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;

    @Autowired
    @Lazy
    private AppointmentsService appointmentsService;

    @Autowired
    private OfficeService officeService;

    @Transactional
    public List<OfficeDoctorAvailability> getAvailableHoursForOffice(Integer officeId) {
        return officeDoctorAvailabilityRepository.findByOfficeAndAvailabilityStatusIsTrue(officeId);
    }

    @Transactional
    public List<OfficeDoctorAvailability> getAvailableHoursForDoctor(String name, String surname) {
        return officeDoctorAvailabilityRepository.findAvailableHoursForDoctor(name, surname);
    }

    @Transactional
    public void reservedAppointment(Integer officeAvailabilityId, Patients patient) {
        OfficeDoctorAvailability availability = officeDoctorAvailabilityRepository.findById(officeAvailabilityId)
                .orElseThrow(() -> new NotFoundException("Slot not found"));

        if (availability.getAvailabilityStatus()) {
            OfficeDoctorAvailability officeDoctorAvailability =
                    availability.withAvailabilityStatus(false);
            officeDoctorAvailabilityRepository.save(officeDoctorAvailability);

            appointmentsService.createScheduledAppointment(officeDoctorAvailability, patient);


        } else {
            throw new SlotNotAvailableException("Slot is already booked");
        }
    }


    @Transactional
    public OfficeDoctorAvailability getOfficeAvailability(Integer officeAvailabilityId) {
        return officeDoctorAvailabilityRepository.findById(officeAvailabilityId)
                .orElseThrow(() -> new NotFoundException("Slot not found"));
    }


    @Transactional
    public void removeAvailability(Integer officeAvailabilityId) {
        officeDoctorAvailabilityRepository.deleteById(officeAvailabilityId);
    }

    @Transactional
    public List<OfficeDoctorAvailability> getUnavailableOfficeHoursForDoctor(Integer doctorId) {
        return officeDoctorAvailabilityRepository.findAvailableStatusIsFalseWithDoctorId(doctorId);
    }

    public String isValid(OfficeDoctorAvailabilityDTO availabilityDTO) {

        if (isFullHourOrHalfHour(availabilityDTO.getStartTime()) || isFullHourOrHalfHour(availabilityDTO.getEndTime())) {
            return "error.fullHourOrHalfHour";
        }

        if (availabilityDTO.getStartTime().isAfter(availabilityDTO.getEndTime())) {
            return "error.startTimeAfterEndTime";
        }

        Duration duration = Duration.between(availabilityDTO.getStartTime(), availabilityDTO.getEndTime());
        if (duration.toMinutes() < 30) {
            return "error.durationTooShort";
        }

        if (exists(availabilityDTO.getOfficeId(), availabilityDTO.getDate(), availabilityDTO.getStartTime(), availabilityDTO.getEndTime())) {
            return "error.availabilityExists";
        }

        return null;
    }

    @Transactional
    public boolean exists(Integer officeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<OfficeDoctorAvailability> existingEntries =
                officeDoctorAvailabilityRepository.findByDateAndTimeRange(date, startTime, endTime, officeId);

        if (!existingEntries.isEmpty()) {
            return true;
        }

        List<OfficeDoctorAvailability> conflictingEntries =
                officeDoctorAvailabilityRepository.findConflictingAppointments(date, startTime, endTime, officeId);

        return !conflictingEntries.isEmpty();
    }

    private boolean isFullHourOrHalfHour(LocalTime time) {
        return time.getMinute() != 0 && time.getMinute() != 30;
    }

    @Transactional
    public void setAvailable(OfficeDoctorAvailability officeDoctorAvailability) {

        Office office = officeService.getOffice(officeDoctorAvailability.getOffice().getOfficeId());



        OfficeDoctorAvailability availability = officeDoctorAvailability.withAvailabilityStatus(true)
                .withOffice(office);
        officeDoctorAvailabilityRepository.save(availability);
    }

    @Transactional
    public OfficeDoctorAvailability getOfficeAvailabilityByStartTimeAndEndTime(
            OffsetDateTime probableStartTime,
            Office office) {

        LocalDate date =
                LocalDate.of(probableStartTime.getYear(), probableStartTime.getMonth(), probableStartTime.getDayOfMonth());

        LocalTime startTime = LocalTime.of(probableStartTime.getHour(), probableStartTime.getMinute());


        return officeDoctorAvailabilityRepository.findByDateAndTime(date, startTime, office.getOfficeId())
                .orElseThrow(() -> new NotFoundException("Office Availability no found"));
    }




}