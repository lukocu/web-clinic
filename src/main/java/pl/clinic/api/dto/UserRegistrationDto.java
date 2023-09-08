package pl.clinic.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    @Size(min = 3, max = 50, message = "Nazwa użytkownika powinna mieć od 3 do 50 znaków")
    @NotNull
    String username;

    @NotBlank(message = "Email jest wymagany")
    @Size(max = 100, message = "Email nie może przekraczać 100 znaków")
    @NotNull
    String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 6, message = "Hasło powinno mieć co najmniej 6 znaków")
    @NotNull
    String password;

    @NotBlank(message = "Potwierdzenie hasła jest wymagane")
    @NotNull
    String confirmPassword;

    String role;
}
