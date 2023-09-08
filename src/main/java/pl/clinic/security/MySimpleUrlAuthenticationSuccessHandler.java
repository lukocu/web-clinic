package pl.clinic.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Tutaj możesz dodać swoje niestandardowe działania po poprawnym zalogowaniu
        // Na przykład przekierowanie na określoną stronę w zależności od roli użytkownika

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("PATIENT"))) {
            response.sendRedirect("/patient_dashboard");
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {
            response.sendRedirect("/doctor_dashboard");
        } else {
            // Domyślne przekierowanie na stronę główną
            response.sendRedirect("/");
        }
    }
}
