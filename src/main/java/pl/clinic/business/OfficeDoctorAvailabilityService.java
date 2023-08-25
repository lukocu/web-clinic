package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OfficeDoctorAvailabilityService {
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;
    private OfficeRepository officeRepository;
    private OfficeService officeService;

    public List<OfficeDoctorAvailability> getOfficeAvailability(Integer officeId) {
        return officeDoctorAvailabilityRepository.findAvailableHoursByOfficeId(officeId);

    }

    public void setOfficeAvailability(Integer officeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Office office = officeRepository.findById(officeId);

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
}