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


}
