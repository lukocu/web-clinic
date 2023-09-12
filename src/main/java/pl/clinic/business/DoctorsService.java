package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorsService {

    private DoctorsRepository doctorsRepository;

    @Transactional
    public Doctors searchDoctor(String name, String surname) {
        Doctors doctor = doctorsRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        return doctor;
    }

    @Transactional
    public List<Doctors> getDoctorsAndOffice() {
        List<Doctors> doctors = doctorsRepository.findDoctorsAndOffice();
        return doctors;
    }

    @Transactional
    public Doctors findByUserId(Integer userId) {
        return doctorsRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }
}
