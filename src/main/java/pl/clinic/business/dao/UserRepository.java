package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private UserJpaRepository userJpaRepository;
    private UserEntityMapper userEntityMapper;


    public void saveNewPatientUser(User user) {
        userJpaRepository.save(userEntityMapper.mapToEntityWithNewPatient(user));
    }

    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(entity -> userEntityMapper.mapFromEntity(entity))
                .toList();
    }

    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userEntityMapper::mapFromEntity);
    }

    public Optional<User> findByUsernameDoctor(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userEntityMapper::mapFromEntityForDoctor);
    }
}
