
![Web Clinic Management](src/main/resources/🏥Web_clinic_management.png)

Web Clinic to aplikacja do zarządzania wizytami w klinice medycznej, stworzona przy użyciu technologii Java i frameworka Spring Boot, z wykorzystaniem bazy danych PostgreSQL. Projekt opiera się na wzorcu MVC (Model-View-Controller) i REST API, oferując osobną konfigurację zabezpieczeń opartą na JWT token dla API oraz mechanizm sesji przy użyciu ciasteczek dla warstwy MVC.

Aplikacja jest zorganizowana w strukturze warstwowej, w której każda warstwa korzysta z dedykowanych modeli, takich jak DTO (Data Transfer Object), Domain i Entity, co pozwala na skuteczne zarządzanie danymi i procesami wewnątrz systemu.


Aplikacja jest przetestowany pod kątem jakości kodu i funkcjonalności za pomocą różnych rodzajów testów:


- Testy jednostkowe korzystają z popularnych narzędzi takich jak JUnit i Mockito. Więcej informacji oraz przykłady testów znajdziesz w katalogu src/test.


- Testy integracyjne wykorzystują technologię Testcontainers, co pozwala na uruchamianie kontenerów Docker podczas testowania oraz Rest Assured.

## Demo

Aby wypróbować aplikację, odwiedź [Demo Web Clinic](http://13.53.197.245:8080/web-clinic/) (aplikacja korzysta z http).

Przykładowi użytkownicy do logowania:
- Dla pacjenta:
    - Username: patient1
    - Hasło: test

- Dla lekarza:
    - Username: doctor1
    - Hasło: test

Dokumentacja API znajduje się w swaggerze [Dokumentacja](http://13.53.197.245:8080/web-clinic/swagger-ui/index.html)
Przykładowe body do zalogowania: (aplikacja w warstwie REST korzysta z autoryzacji JWT)
```json
{
  "username": "patient1",
  "password": "test"
}
```
```json
{
  "username": "doctor2",
  "password": "test"
}
```
## Funkcje

Aplikacja Web Clinic oferuje wiele funkcji, w tym:

- Rejestracja pacjentów i lekarzy.
- Przeglądanie dostępnych terminów wizyt.
- Rezerwacja i zarządzanie wizytami.
- Przeglądanie historii wizyt.
- Lekarz po odbyciu wizyty z pacjentem, może wprowadzić notatkę do wizyty.
- Aplikacja daje lekarzom możliwość określenia ich dat dostępności na potencjalne wizyty.
- Pacjent może zobaczyć historię odbytych wizyt i sprawdzić, kiedy wypadają nadchodzące wizyty.
- Pacjent może sprawdzić, co lekarz napisał w ramach notatki do odbytej wizyty.
- Pacjent może odwołać wizytę.

## Technologie
- Java 17
- Spring MVC i REST Controller
- Spring Boot 3.1
    -  Spring Data JPA, Thymeleaf, Validation, Web, Security (session, jwt)
-  SpringDoc OpenAPI (Swagger)
- Java JSON Web Token (JWT)
-  Project Lombok
-  MapStruct
-  PostgreSQL
-  Flyway
- SL4J, Logback
- Docker (deploy)
- AWS for demo
- Test:
    - Spring Security Test
    - Spring Boot Starter Test (MockMVC, Mockito, DataJpaTest)
    - Testcontainers
    - JUnit 5
    - RestAssured
    - JaCoCo

## Instalacja i Uruchamianie

🐳 W celu wykonania testów wymagane jest [zainstalowanie dockera](https://docs.docker.com/engine/install/)

Jeśli chcesz uruchomić tę aplikację lokalnie na swoim komputerze, wykonaj następujące kroki:

1. Pobierz kod źródłowy z tego repozytorium:

   ```shell
   git clone https://github.com/lukocu/web-clinic.git
    ```

2. Pliki konfiguracyjne do bazy danych znajdują sie w `src/main/resources/application.properties`


3. Dokumentacja api (Swagger) powinna się znajdować pod:
  ` localhost:XXXX/web-clinic/swagger-ui/index.html`

## Diagram bazy danych

Baza danych generuje w tabelach klucze za pomocą sekwencji które są zdefiniowane w migracjach Flyway, dane rozruchowe są pobierane również z migracji Flyway.
Diagram jest skolalizowany w `src/main/resources/Diagram_ERD.png`


![Diagram_ERD](src/main/resources/Diagram_ERD.png)