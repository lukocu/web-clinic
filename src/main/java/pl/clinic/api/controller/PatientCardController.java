package pl.clinic.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.PatientsCardMapper;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.PatientCardService;
import pl.clinic.business.PatientsService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientCardController {


    public static final String PATIENT_CARD = "/patient_dashboard/patient_card";
    private PatientCardService patientCardService;
    private PatientsCardMapper patientsCardMapper;
    private PatientsService patientService;
    private PatientsMapper patientsMapper;
    @PostMapping(PATIENT_CARD)
    public String goToPatientCard(
            @RequestParam Integer patientId,
            HttpSession session) {

        session.setAttribute("patientId", patientId);

        return "redirect:/patient_dashboard/patient_card";
    }

    @GetMapping(PATIENT_CARD)
    public String showPatientCard(HttpSession session, Model model) {

        Integer patientId = (Integer) session.getAttribute("patientId");
        PatientsDTO patientsDTO = patientsMapper.mapToDto(patientService.getPatient(patientId));

        List<PatientCardDTO> patientCardDTO =
              patientCardService.getPatientCards(patientId).stream()
                      .map(card-> patientsCardMapper.mapToDtoWithDoc(card))
                      .toList();


        model.addAttribute("patientDTO",patientsDTO);
        model.addAttribute("patientCardDTO",patientCardDTO);

        return "patient_card";
    }
}
