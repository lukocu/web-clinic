package pl.clinic.business.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.repository.jpa.OfficeJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.OfficeEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OfficeRepository {
    private OfficeJpaRepository officeJpaRepository;
    private OfficeEntityMapper officeEntityMapper;

    public Optional<Office> findById(Integer officeId) {
        return officeJpaRepository.findById(officeId)
                .map(entity -> officeEntityMapper.mapFromEntity(entity));
    }
}
