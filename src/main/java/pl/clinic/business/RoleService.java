package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.domain.Role;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.infrastructure.database.repository.jpa.RoleJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.RoleEntityMapper;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleJpaRepository roleJpaRepository;
    private RoleEntityMapper roleEntityMapper;
    public Role findByName(String role){
        return roleJpaRepository.findByRole(role)
                .map(a->roleEntityMapper.mapFromEntity(a))
                .orElseThrow(() ->new NotFoundException("Error: Role is not found."));
    }
}
