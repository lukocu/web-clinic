package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.DoctorUserDTO;
import pl.clinic.api.dto.PatientUserDTO;
import pl.clinic.business.dao.UserRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Patients;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;
import pl.clinic.domain.exception.NotFoundException;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PatientsService patientsService;
    private PasswordEncoder passwordEncoder;
    private DoctorsService doctorsService;

    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsernameDoctor(String username) {
        return userRepository.findByUsernameDoctor(username)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Transactional
    public User findByUsernameForPatient(String username) {
        return userRepository.findByUsernameForPatient(username)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Transactional
    public Patients registerNewPatientUser(PatientUserDTO patientUserDTO) {
        String encodedPassword = passwordEncoder.encode(patientUserDTO.getPassword());

        User newUser = User.builder()
                .username(patientUserDTO.getUsername())
                .email(patientUserDTO.getEmail())
                .password(encodedPassword)
                .roles(Set.of(Role.builder()
                        .roleId(3)
                        .role("PATIENT")
                        .build()))
                .active(true)
                .build();


        Patients newPatient = Patients.builder()
                .name(patientUserDTO.getName())
                .surname(patientUserDTO.getSurname())
                .pesel(patientUserDTO.getPesel())
                .birthDate(patientUserDTO.getBirthDate())
                .address(patientUserDTO.getAddress())
                .phone(patientUserDTO.getPhone())
                .user(newUser)
                .build();


        return patientsService.saveNewPatient(newPatient);

    }

    @Transactional
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public Doctors registerNewDoctorUser(DoctorUserDTO doctorUserDTO) {
        String encodedPassword = passwordEncoder.encode(doctorUserDTO.getPassword());

        User newUser = User.builder()
                .username(doctorUserDTO.getUsername())
                .email(doctorUserDTO.getEmail())
                .password(encodedPassword)
                .roles(Set.of(Role.builder()
                        .roleId(2)
                        .role("DOCTOR")
                        .build()))
                .active(true)
                .build();


        Doctors newDoctor = Doctors.builder()
                .name(doctorUserDTO.getName())
                .surname(doctorUserDTO.getSurname())
                .pesel(doctorUserDTO.getPesel())
                .phone(doctorUserDTO.getPhone())
                .user(newUser)
                .build();

        return doctorsService.addDoctor(newDoctor);
    }

}
