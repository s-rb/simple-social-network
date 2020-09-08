package ru.list.surkovr.social_network_simple.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.list.surkovr.social_network_simple.domain.User;
import ru.list.surkovr.social_network_simple.repositories.UserDetailsRepository;

import java.time.LocalDateTime;

@EnableWebSecurity
@EnableOAuth2Sso
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepository userDetailsRepository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = userDetailsRepository.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setGender((String) map.get("gender"));
                newUser.setUserpic((String) map.get("picture"));
                return newUser;
            });

            user.setLastVisit(LocalDateTime.now());
            return userDetailsRepository.save(user);
        };
    }
}
