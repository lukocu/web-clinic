package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private DoctorsRepository doctorsRepository;
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;

    public List<OfficeDoctorAvailability> getAvailableHoursForDoctor(String name, String surname) {
        return officeDoctorAvailabilityRepository.findAvailableHoursForDoctor(name, surname);
    }
}
