package pl.clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.*;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.security.IAuthenticationFacade;
import pl.clinic.util.DomainData;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VisitController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PatientCardService patientCardService;
    @MockBean
    private PatientsService patientService;
    @MockBean
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    @MockBean
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    @MockBean
    private OfficeService officeService;
    @MockBean
    private AppointmentsService appointmentsService;
    @MockBean
    private IAuthenticationFacade authenticationFacade;
    @MockBean
    private UserService userService;
    @MockBean
    private DoctorsService doctorsService;


    @Test
    @WithMockUser(username = "doctor2", roles = "DOCTOR")
    public void testAddPatientCard() throws Exception {


        when(authenticationFacade.getAuthentication()).thenReturn(mock(Authentication.class));

        mockMvc.perform(post("/doctor_dashboard/visit/{officeAvailabilityId}/add_patient_card", 1)
                        .param("diagnosisNote", "Przykładowa diagnoza")
                        .param("prescriptionDate", "2023-10-10T10:00")
                        .param("prescriptionDateEnd", "2023-10-20T10:00")
                        .param("medicationsData", "[]")
                        .param("diseaseData", "[]")
                        .param("patientPesel", "12345678901"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "doctor2", roles = "DOCTOR")
    public void testDeleteVisit() throws Exception {

        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor2_1();
        Appointments appointments = DomainData.appointment2();

        when(officeDoctorAvailabilityService.getOfficeAvailability(anyInt())).thenReturn(availability);
        when(appointmentsService.getCurrentAppointmentWithOffice(any(), any(), any())).thenReturn(appointments);


        mockMvc.perform(delete("/doctor_dashboard/visit/{officeAvailabilityId}/finish_visit", 2))
                .andExpect(status().is3xxRedirection());
    }
}
