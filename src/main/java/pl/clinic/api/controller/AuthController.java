package pl.clinic.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.UserService;
import pl.clinic.api.dto.mapper.UserRegistrationMapper;
import pl.clinic.domain.Patients;
import pl.clinic.domain.User;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private UserRegistrationMapper userRegistrationMapper;
    private PatientsMapper patientsMapper;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationUserDto", new UserRegistrationDto());
        model.addAttribute("registrationPatientDto", new PatientsDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("registrationUserDto") @Valid UserRegistrationDto registrationDto,
            @ModelAttribute("registrationPatientDto") PatientsDTO patientsDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return "registration";
        }
        registrationDto.setRole("PATIENT");

        User patientUser = userRegistrationMapper.mapFromDto(registrationDto);
        Patients patient = patientsMapper.mapFromDtoWithoutAppointment(patientsDTO);

        userService.registerNewPatientUser(patientUser,patient);
        return "redirect:/login"; // Przekierowanie na stronę logowania po zarejestrowaniu użytkownika
    }

    @GetMapping("/users")
    public String users(Model model){

        List<UserRegistrationDto> users = userService.findAllUsers().stream()
                .map(user -> userRegistrationMapper.mapToDto(user))
                .toList();
        model.addAttribute("users", users);
        return "users";
    }


}
