package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.UserRepository;
import pl.clinic.domain.Patients;
import pl.clinic.domain.User;
import pl.clinic.domain.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    @Transactional
    public void registerNewPatientUser(User patientUser, Patients patient) {

        userRepository.saveNewPatientUser(patientUser.withPatient(patient));

    }

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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }
}
