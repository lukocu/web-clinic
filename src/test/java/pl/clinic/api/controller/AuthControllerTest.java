package pl.clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.api.dto.mapper.UserRegistrationMapper;
import pl.clinic.business.UserService;
import pl.clinic.domain.User;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRegistrationMapper userRegistrationMapper;


    @Test
    public void testLoginPage() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "PATIENT")
    public void testRegisterPatientAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration/register")
                        .param("username", "testuser")
                        .param("password", "testpassword")
                        .param("confirmPassword", "testpassword")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("birthDate", "1990-01-01")
                        .param("email", "test@example.com")
                        .param("phoneNumber", "123456789"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }


    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    public void testUsers() throws Exception {

        List<User> userList = List.of(DomainData.user1(), DomainData.user2());



        when(userService.findAllUsers()).thenReturn(userList);
        when(userRegistrationMapper.mapToDto(any(User.class))).thenReturn(new UserRegistrationDto());



        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"));


        verify(userService, times(1)).findAllUsers();
        verify(userRegistrationMapper, times(userList.size())).mapToDto(any(User.class));
    }
}
