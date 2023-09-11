package pl.clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
