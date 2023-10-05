package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.api.dto.SpecializationDTO;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.exception.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DoctorsService {

    private DoctorsRepository doctorsRepository;


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

    @Transactional
    public List<Doctors> getAllDoctors() {
        return doctorsRepository.findAll();
    }

    @Transactional
    public Doctors addDoctor(Doctors doctor) {
        return doctorsRepository.save(doctor);
    }

    @Transactional
    public void updateDoctor(Integer doctorId, Doctors doctor) {

        Doctors existingDoctor = doctorsRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));


        Doctors updatedDoctor = existingDoctor
                .withName(doctor.getName())
                .withSurname(doctor.getSurname())
                .withSpecializations(doctor.getSpecializations());


        doctorsRepository.updateDoctor(updatedDoctor);
    }

    @Transactional
    public void deleteDoctor(Integer doctorId) {
        Doctors existingDoctor = doctorsRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        // Usuń lekarza z repozytorium
        doctorsRepository.deleteById(existingDoctor.getDoctorId());
    }

    @Transactional
    public Doctors getDoctor(Integer doctorId) {
        return doctorsRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }
}
