package pl.clinic.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.api.dto.mapper.PatientsCardMapper;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.PatientCardService;
import pl.clinic.business.PatientsService;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient-card")
@AllArgsConstructor
public class PatientCardRestController {

    private final PatientCardService patientCardService;
    private final PatientsService patientService;
    private final PatientsCardMapper patientsCardMapper;
    private final PatientsMapper patientsMapper;

    @GetMapping("/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT','DOCTOR')")
    public ResponseEntity<?> getPatientCard(@PathVariable Integer patientId) {
        try {
            PatientsDTO patientsDTO = patientsMapper.mapToDto(patientService.getPatient(patientId));
            List<PatientCardDTO> patientCardDTO = patientCardService.getPatientCards(patientId).stream()
                    .map(patientsCardMapper::mapToDtoWithDoc)
                    .toList();

            Map<String, Object> response = new HashMap<>();
            response.put("patientDTO", patientsDTO);
            response.put("patientCardDTO", patientCardDTO);

            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<?> createPatientCard( @RequestBody PatientCardDTO request) {
        try {


            PatientCard savedPatientCard =
                    patientCardService.savePatientCard(patientsCardMapper.mapFromDto(request));

            PatientCardDTO savedPatientCardDTO = patientsCardMapper.mapToDtoWithDoc(savedPatientCard);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatientCardDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{patientCardId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<?> deletePatientCard(@PathVariable Integer patientCardId) {
        try {
            patientCardService.deletePatientCard(patientCardId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
