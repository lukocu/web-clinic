package pl.clinic.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.clinic.infrastructure.database.entity.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
