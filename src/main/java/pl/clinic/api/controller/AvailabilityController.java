package pl.clinic.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.api.dto.mapper.OfficeMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.OfficeService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.security.AuthenticationFacade;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AvailabilityController {

    public static final String ADD="/doctor_dashboard/add_availability";
    public static final String ADD_POST="/doctor_dashboard/add_availability/add";
    public static final String REMOVE="/doctor_dashboard/remove_availability";
    public static final String REMOVE_DELETE="/doctor_dashboard/remove_availability/{officeAvailabilityId}";

    private final OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private UserService userService;
    private AuthenticationFacade authenticationFacade;
    private DoctorsService doctorsService;
    private OfficeService officeService;
    private OfficeMapper officeMapper;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;

    @GetMapping(ADD)
    public String showAddAvailabilityForm(Model model, HttpSession session) {
        Authentication authentication = authenticationFacade.getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            var user = userService.findByUsernameDoctor(userDetails.getUsername());
            Doctors doctor = doctorsService.findByUserId(user.getUserId());

            List<OfficeDTO> availableOffices = officeService.getOffices(doctor.getDoctorId()).stream()
                    .map(office -> officeMapper.mapToDto(office))
                    .toList();

            OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO = new OfficeDoctorAvailabilityDTO();


            List<OfficeDTO> sessionAvailableOffices = (List<OfficeDTO>) session.getAttribute("availableOffices");


            if (sessionAvailableOffices == null) {
                sessionAvailableOffices = new ArrayList<>();
            }
            sessionAvailableOffices.addAll(availableOffices);


            List<OfficeDoctorAvailability> addedAvailabilities = (List<OfficeDoctorAvailability>) session.getAttribute("addedAvailabilities");

            if (addedAvailabilities == null) {
                addedAvailabilities = new ArrayList<>();
            }

            model.addAttribute("availabilityDTO", officeDoctorAvailabilityDTO);
            model.addAttribute("availableOffices", availableOffices);
            model.addAttribute("addedAvailabilities", addedAvailabilities);

            return "add_availability";
        }

        return "error";
    }

    @PostMapping(ADD_POST)
    public String addAvailability(@ModelAttribute("availabilityDTO") OfficeDoctorAvailabilityDTO availabilityDTO,
                                  Model model,
                                  HttpSession session) {
        String errorCode = officeDoctorAvailabilityService.isValid(availabilityDTO);

        if (errorCode != null) {
            String errorMessage = translateErrorCodeToMessage(errorCode);
            model.addAttribute("error", errorMessage);
            return "add_availability";
        }

        OfficeDoctorAvailability officeDoctorAvailability = officeDoctorAvailabilityMapper
                .mapFromDtoWithOffice(availabilityDTO);
        officeDoctorAvailabilityService.setAvailable(officeDoctorAvailability);

        model.addAttribute("success", "Success! entity was added");

        List<OfficeDoctorAvailability> addedAvailabilities =
                (List<OfficeDoctorAvailability>) session.getAttribute("addedAvailabilities");

        if (addedAvailabilities == null) {
            addedAvailabilities = new ArrayList<>();
        }

        addedAvailabilities.add(officeDoctorAvailability);

        session.setAttribute("addedAvailabilities", addedAvailabilities);

        return  "redirect:/doctor_dashboard/add_availability";
    }

    @GetMapping(REMOVE)
    public String showRemoveAvailabilityPage(Model model) {

        Authentication authentication = authenticationFacade.getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            var user = userService.findByUsernameForPatient(userDetails.getUsername());
            Doctors doctor = doctorsService.findByUserId(user.getUserId());

            List<OfficeDoctorAvailabilityDTO> doctorAvailabilities =
                    officeDoctorAvailabilityService.getAvailableHoursForDoctor(doctor.getName(),doctor.getSurname())
                            .stream()
                            .map(availability->officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(availability))
                            .toList();


            model.addAttribute("doctorAvailabilities", doctorAvailabilities);

            return "remove_availability";
        }
        return "error";
    }

    @DeleteMapping(REMOVE_DELETE)
    public String removeAvailability(@PathVariable("officeAvailabilityId") Integer officeAvailabilityId) {

        officeDoctorAvailabilityService.removeAvailability(officeAvailabilityId);

        return "redirect:/doctor_dashboard/remove_availability";
    }

    private String translateErrorCodeToMessage(String errorCode) {

        if ("error.fullHourOrHalfHour".equals(errorCode)) {
            return "You can only schedule appointments at full hours or half-hours.";
        } else if ("error.startTimeAfterEndTime".equals(errorCode)) {
            return "End time must be after start time.";
        } else if ("error.durationTooShort".equals(errorCode)) {
            return "Time duration must be at least 30 minutes.";
        } else if ("error.availabilityExists".equals(errorCode)) {
            return "This availability already exists.";
        } else {
            return "An error occurred.";
        }
    }

}


