package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.domain.Office;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface OfficeMapper {
  /*  OfficeDTO mapToDto(Office office);
    Set<OfficeDTO> mapToDtoSet(Set<Office> offices);*/
}
