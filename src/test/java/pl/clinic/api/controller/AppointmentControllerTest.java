package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.UserService;
import pl.clinic.security.IAuthenticationFacade;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentControllerTest {


    private MockMvc mockMvc;

    @MockBean
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;

    @MockBean
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private IAuthenticationFacade authenticationFacade;

    @Test
    @WithMockUser(username = "patient1", roles = "PATIENT")
    public void testShowAvailableList() throws Exception {
        // Given
        Integer officeId = 2;
        List<OfficeDoctorAvailabilityDTO> availabilityDTOs = Collections.emptyList();
        when(officeDoctorAvailabilityService.getAvailableHoursForOffice(officeId)).thenReturn(Collections.emptyList());

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/{officeId}", officeId)
                        .param("officeId", officeId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("appointment"))
                .andExpect(MockMvcResultMatchers.model().attribute("availableList", availabilityDTOs));
    }




    @Test
    @WithMockUser(username = "patient1", roles = "PATIENT")
    public void testShowSuccessPage() throws Exception {
        // Given
        OfficeDoctorAvailabilityDTO availability = new OfficeDoctorAvailabilityDTO();

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/appointment/success").session(new MockHttpSession()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("success"))
                .andExpect(MockMvcResultMatchers.model().attribute("availability", availability));
    }
}
