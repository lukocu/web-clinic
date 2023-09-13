package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.User;
import pl.clinic.security.IAuthenticationFacade;

import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class DoctorController {
    public static final String DOCTOR_LIST = "/doctor_list";
    public static final String DOCTOR_DASHBOAR = "/doctor_dashboard";

    private final DoctorsService doctorsService;
    private final DoctorMapper doctorMapper;
    private final IAuthenticationFacade authenticationFacade;
    private final UserService userService;
    private final OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    private final OfficeDoctorAvailabilityService officeDoctorAvailabilityService;



    @GetMapping(value = DOCTOR_DASHBOAR)
    public String getDoctorDashboar(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Doctors doctor = doctorsService.findByUserId(user.getUserId());
            DoctorDTO doctorDTO = doctorMapper.mapToDtoSpecAndOffices(doctor);

            List<OfficeDoctorAvailabilityDTO> unavailableOfficeHours =
                    officeDoctorAvailabilityService.getUnavailableOfficeHours(doctor.getDoctorId()).stream()
                            .map(officeDoctorAvailabilityMapper::mapToDto)
                            .sorted(Comparator.comparing(OfficeDoctorAvailabilityDTO::getDate)
                                    .thenComparing(OfficeDoctorAvailabilityDTO::getStartTime))
                            .toList();

         /*   List<OfficeDoctorAvailabilityDTO> sortedAvailabilities = doctor.getOffices().stream()
                    .flatMap(office -> office.getOfficeDoctorAvailabilities().stream())
                    .map(officeDoctorAvailabilityMapper::mapToDto)
                    .sorted(Comparator.comparing(OfficeDoctorAvailabilityDTO::getDate)
                            .thenComparing(OfficeDoctorAvailabilityDTO::getStartTime))
                    .toList();*/

            model.addAttribute("doctorDTO",doctorDTO);
            model.addAttribute("sortedAvailabilities", unavailableOfficeHours);
            return "doctor_dashboard";

        }

        return "login";
    }


    @GetMapping(value = DOCTOR_LIST)
    public String homePage(Model model) {
        var doctors = doctorsService.getDoctorsAndOffice().stream()
                .map(doctorMapper::mapToDtoSpecAndOffices)
                .toList();


        model.addAttribute("doctorsAndOffice", doctors);


        return "doctor_list";
    }
}
