package pl.clinic.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.controller.rest.payload.response.MessageResponse;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.DoctorUserDTO;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {

    @Autowired
    private DoctorsService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT','DOCTOR')")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors().stream()
                .map(doctor -> doctorMapper.mapToDtoSpecAndOffices(doctor))
                .toList();
        return ResponseEntity.ok(doctors);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addDoctor(@RequestBody DoctorUserDTO doctorUserDTO) {

        if (userService.existsByEmail(doctorUserDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByUsername(doctorUserDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


     DoctorDTO createdDoctor = doctorMapper.mapToDto(
             userService.registerNewDoctorUser(doctorUserDTO));


        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }


    @PutMapping("/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<String> updateDoctor(@PathVariable Integer doctorId,
                                                  @RequestBody DoctorDTO doctorDTO) {
         doctorService.updateDoctor(doctorId, doctorMapper.mapFromDtoSpecAndOffices(doctorDTO));

        return ResponseEntity.ok("Updated success");
    }



}