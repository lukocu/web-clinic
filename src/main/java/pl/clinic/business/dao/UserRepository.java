package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.clinic.infrastructure.database.repository.jpa.UserJpaRepository;

@Repository
@AllArgsConstructor
public class UserRepository {
    private UserJpaRepository userJpaRepository;
    private UserEntityMapper userEntityMapper;


    public void saveNewPatientUser(User user) {
        userJpaRepository.save(userEntityMapper.mapToEntityWithNewPatient(user));
    }
}
