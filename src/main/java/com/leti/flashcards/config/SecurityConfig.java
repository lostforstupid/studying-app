package com.leti.flashcards.config;

import com.leti.flashcards.user.User;
import com.leti.flashcards.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SUB = "sub";
    private static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PICTURE = "picture";

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    protected void configure(HttpSecurity http) {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login**", "/js/**", "/css/**", "/error**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
            String id = (String) map.get(SUB);

            return userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get(NAME));
                newUser.setEmail((String) map.get(EMAIL));
                newUser.setProfilePictureUrl((String) map.get(PICTURE));
                return userRepository.save(newUser);
            });
        };
    }
}
