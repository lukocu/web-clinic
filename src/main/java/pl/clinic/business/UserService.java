package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.PatientUserDTO;
import pl.clinic.business.dao.UserRepository;
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
    public void registerNewPatientUser(PatientUserDTO patientUserDTO) {
        User newUser = User.builder()
                .username(patientUserDTO.getUsername())
                .email(patientUserDTO.getEmail())
                .password(patientUserDTO.getPassword())
                .roles(Set.of(Role.builder()
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


        patientsService.saveNewPatient(newPatient);





    }
}
