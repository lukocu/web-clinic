package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.business.DoctorsService;

@Controller
@AllArgsConstructor
public class DoctorController {
    public static final String DOCTOR="/doctor";

    private final DoctorsService doctorsService;
    private final DoctorMapper doctorMapper;

    @GetMapping(value =  DOCTOR)
    public String homePage(Model model){
       var doctors= doctorsService.getDoctorsAndOffice().stream()
               .map(doctorMapper::mapAdditionalFields)
               .toList();

       model.addAttribute("doctorsAndOffice",doctors);


        return "doctor_portal";
    }
}
