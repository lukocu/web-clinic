package pl.clinic.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.clinic.api.dto.PatientUserDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.UserService;
import pl.clinic.api.dto.mapper.UserRegistrationMapper;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Patients;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private UserRegistrationMapper userRegistrationMapper;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {

        model.addAttribute("patientUserDTO", new PatientUserDTO());

        return "registration";
    }

    @PostMapping("/registration/register")
    public String registerPatientAccount(
    @Valid PatientUserDTO patientUserDTO,
    BindingResult result){

        if (result.hasErrors()) {
            return "registration";
        }

        userService.registerNewPatientUser(patientUserDTO);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String users(Model model) {

        List<UserRegistrationDto> users = userService.findAllUsers().stream()
                .map(user -> userRegistrationMapper.mapToDto(user))
                .toList();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {

            new SecurityContextLogoutHandler().logout(request, null, auth);
        }


        return "redirect:/login";
    }

}
