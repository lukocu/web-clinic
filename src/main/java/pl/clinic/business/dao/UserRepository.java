package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.UserEntity;
import pl.clinic.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.UserEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private UserJpaRepository userJpaRepository;
    private UserEntityMapper userEntityMapper;


    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(entity -> userEntityMapper.mapFromEntity(entity))
                .toList();
    }

    public Optional<User> findByUsernameForPatient(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userEntityMapper::mapFromEntityForPatient);
    }

    public Optional<User> findByUsernameDoctor(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userEntityMapper::mapFromEntityForDoctor);
    }


    public boolean existsByUsername(String username) {
        Optional<UserEntity> byUsername = userJpaRepository.findByUsername(username);
        return byUsername.isPresent();
    }

    public boolean existsByEmail(String email) {
        Optional<UserEntity> byUsername = userJpaRepository.findByEmail(email);
        return byUsername.isPresent();
    }

    public void save(User user) {
        userJpaRepository.save(userEntityMapper.mapToEntityWith(user));
    }

    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userEntityMapper::mapFromEntity);
    }
}
