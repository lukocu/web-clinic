package pl.clinic.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.AppointmentsMapper;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.security.rest.UserDetailsImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(AppointmentRestController.APPOINTMENT)
public class AppointmentRestController {

    public static final String APPOINTMENT = "api/appointment";
    public static final String APPOINTMENT_LIST = "/{officeId}";

    public static final String APPOINTMENT_BOOK = "api/appointment/{officeId}/book";
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    private AppointmentsService appointmentsService;
    private AppointmentsMapper appointmentsMapper;
    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value = APPOINTMENT_LIST)
    public List<OfficeDoctorAvailabilityDTO> showAvailableList(@PathVariable Integer officeId) {
        return officeDoctorAvailabilityService.getAvailableHoursForOffice(officeId).stream()
                .map(availability -> officeDoctorAvailabilityMapper.mapToDto(availability))
                .toList();
    }
  /*  @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(value = APPOINTMENT_BOOK)
    public String createAppointment(
            @PathVariable Integer officeId,
            @RequestParam("officeAvailabilityId") Integer officeAvailabilityId,
            @ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability,
            Authentication authentication) {

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        officeDoctorAvailabilityService.createAppointment(officeAvailabilityId);

    }

    @GetMapping("/appointment/success")
    public String showSuccessPage(@ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability) {

    }*/



}
