package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.domain.exception.SlotNotAvailableException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OfficeDoctorAvailabilityService {
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;
    private OfficeRepository officeRepository;
    private AppointmentsService appointmentsService;

    @Transactional
    public void setOfficeAvailability(Integer officeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new NotFoundException("office not found"));

        if (office != null) {
            OfficeDoctorAvailability availability = OfficeDoctorAvailability.builder()
                    .date(date)
                    .startTime(startTime)
                    .endTime(endTime)
                    .availabilityStatus(true)
                    .office(office)
                    .build();

            officeDoctorAvailabilityRepository.save(availability);
        } else {
            // Obsłuż przypadek, gdy biuro o podanym ID nie zostało znalezione

        }

    }

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
                    availability.withAvailabilityStatus(false);// Zmiana statusu dostępności na zarezerwowany
            officeDoctorAvailabilityRepository.save(officeDoctorAvailability);

            appointmentsService.createScheduledAppointment(officeDoctorAvailability, patient);


        } else {
            throw new SlotNotAvailableException("Slot is already booked");
        }
    }


    @Transactional
    public void markSlotAsAvailable(Integer officeAvailabilityId) {
        OfficeDoctorAvailability availability = officeDoctorAvailabilityRepository.findById(officeAvailabilityId)
                .orElseThrow(() -> new NotFoundException("Slot not found"));

        if (!availability.getAvailabilityStatus()) {
            OfficeDoctorAvailability updatedAvailability = availability.withAvailabilityStatus(true);
            officeDoctorAvailabilityRepository.save(updatedAvailability);
        }
    }

}