package pl.clinic.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.controller.rest.payload.response.MessageResponse;
import pl.clinic.api.dto.PatientUserDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.PatientsService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Patients;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    @Autowired
    private PatientsService patientService;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientsMapper patientMapper;



    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PatientsDTO>> getAllPatients() {
        List<PatientsDTO> patients = patientService.getAllPatients().stream()
                .map(patient -> patientMapper.mapToDto(patient))
                .toList();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public ResponseEntity<PatientsDTO> getPatientDetails(@PathVariable Integer patientId) {
      PatientsDTO patients =patientMapper.mapToDto(patientService.getPatient(patientId));

        return ResponseEntity.ok(patients);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> registerPatient(@RequestBody PatientUserDTO patientUserDTO) {

        if (userService.existsByEmail(patientUserDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        if (userService.existsByUsername(patientUserDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already in use!"));
        }

        else {
            Patients patients = userService.registerNewPatientUser(patientUserDTO);

            PatientsDTO registeredPatient = patientMapper.mapToDto(patients);

            return ResponseEntity.ok(registeredPatient);
        }
    }

    @PutMapping("/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PatientsDTO> updatePatient(@PathVariable Integer patientId, @RequestBody PatientsDTO patientDTO) {
        Patients patient = patientService.updatePatient(patientId, patientMapper.mapFromDto(patientDTO));

        PatientsDTO updatedPatient = patientMapper.mapToDto(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {

        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }


}
