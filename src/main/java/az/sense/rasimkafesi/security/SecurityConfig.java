package az.sense.rasimkafesi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeRequests(request -> {
                    // Sadece ADMIN rolüne sahip olanlar /dashboard/** endpointlerine erişebilir
                    request.requestMatchers("/admin/**").hasRole("ADMIN");
                    request.requestMatchers("/book/**").authenticated();
                    request.anyRequest().permitAll(); // Diğer tüm istekler için erişime izin ver
                })
                .formLogin(form -> {
                    form.loginPage("/login") // Giriş sayfası
                            .defaultSuccessUrl("/") // Başarılı giriş sonrası yönlendirme
                            .failureUrl("/login?auth=false"); // Başarısız giriş sonrası yönlendirme
                });

        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }
}