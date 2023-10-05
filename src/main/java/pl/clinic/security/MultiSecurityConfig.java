package pl.clinic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.clinic.security.rest.AuthTokenFilter;

@Configuration
public class MultiSecurityConfig {

    private final UserDetailsService userDetailsService;

    public MultiSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
  /*  @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.clinic.controller"))
                .paths(PathSelectors.any())
                .build();
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationManager authManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailService
    )
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityEnabled(HttpSecurity http, UserDetailsService userDetailService, AuthenticationManager authManager) throws Exception {
        http
                .securityMatcher("/appointment/**", "/patient_card/**", "/patient_dashboard/**", "/login",
                        "/registration/**", "/visit/**", "/doctor_dashboard/**", "/users")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/", "/login", "/error", "/registration/**")
                                .permitAll()
                                .requestMatchers("/appointment/**", "/patient_dashboard/**")
                                .hasAnyAuthority("PATIENT")
                                .requestMatchers("/doctor_dashboard/**", "/visit/**")
                                .hasAnyAuthority("DOCTOR")
                                .requestMatchers("/doctor_list/**", "/patient_card")
                                .hasAnyAuthority("PATIENT", "DOCTOR")
                                .requestMatchers("/appointment/{officeId}/book")
                                .hasRole("PATIENT")
                                .requestMatchers("/users")
                                .hasRole("ADMIN")
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(myAuthenticationSuccessHandler())
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .userDetailsService(userDetailService)
                .authenticationManager(authManager);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityEnabledApi(HttpSecurity http, UserDetailsService userDetailService, AuthTokenFilter authTokenFilter) throws Exception {
        http
                .securityMatcher("/api/**","/v2/**","/v3/**" ,"/swagger-resources/**","/configuration/**","/webjars/**","/swager-ui.html","/swagger-resources")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize ->
                        authorize
                                .requestMatchers("/api/", "/api/auth/**", "/api/error","/api/**","/v2/**","/v3/**" ,"/swagger-resources/**","/configuration/**","/webjars/**","/swager-ui.html","/swagger-resources")
                                .permitAll()
                                .requestMatchers("/api/appointments/schedule/{patientId}",
                                        "/api/appointments/patient/{patientId}", "/api/appointments/date/{officeId}")
                                .hasAnyAuthority("PATIENT")
                                .requestMatchers("/api/appointments/update-status/{appointmentId}")
                                .hasAnyAuthority("DOCTOR")
                                .requestMatchers("/api/patients/{patientId}")
                                .hasAnyAuthority("PATIENT", "DOCTOR")
                                .requestMatchers("/api/patient-card/{patientId}", "/api/patient-card/{patientCardId}",
                                        "/api/doctors/{doctorId}")
                                .hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/api/patient-card/{patientId}", "/api/doctors")
                                .hasAnyAuthority("PATIENT", "DOCTOR", "ADMIN")
                                .requestMatchers("/api/doctors/add", "/api/doctors/delete/**")
                                .hasRole("ADMIN")
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
}
