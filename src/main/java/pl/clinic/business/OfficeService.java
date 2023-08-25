package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OfficeService {

    private OfficeRepository officeRepository;
    private DoctorsRepository doctorsRepository;
    private OfficeDoctorAvailabilityRepository officeDoctorAvailabilityRepository;


        public Set<Office> getDoctorOffices(String pesel) {
            Doctors doctor = doctorsRepository.findByPesel(pesel);

            if (doctor != null) {
                return doctor.getOffices();

            } else {
                // Obsłuż przypadek, gdy lekarz o podanym PESEL-u nie został znaleziony
                return null;
            }
        }



}