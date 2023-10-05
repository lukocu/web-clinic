package pl.clinic.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.domain.Doctors;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {

    @Autowired
    private DoctorsService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors().stream()
                .map(doctor->doctorMapper.mapToDtoSpecAndOffices(doctor))
                .toList();
        return ResponseEntity.ok(doctors);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctors doctor = doctorService.addDoctor(doctorMapper.mapFromDto(doctorDTO));
        DoctorDTO createdDoctor = doctorMapper.mapToDto(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }


    @PutMapping("/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Integer doctorId,
                                                  @RequestBody DoctorDTO doctorDTO) {
        Doctors doctor = doctorService.updateDoctor(doctorId, doctorMapper.mapFromDto(doctorDTO));

        DoctorDTO updatedDoctor = doctorMapper.mapToDto(doctor);

        return ResponseEntity.ok(updatedDoctor);
    }



}