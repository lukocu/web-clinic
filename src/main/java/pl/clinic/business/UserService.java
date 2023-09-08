package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.UserRepository;
import pl.clinic.domain.Patients;
import pl.clinic.domain.User;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    @Transactional
    public void registerNewPatientUser(User patientUser, Patients patient) {

        userRepository.saveNewPatientUser(patientUser.withPatient(patient));


    }
}
