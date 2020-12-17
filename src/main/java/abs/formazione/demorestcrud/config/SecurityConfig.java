package abs.formazione.demorestcrud.config;

import abs.formazione.demorestcrud.security.CustomUserDetailsService;
import abs.formazione.demorestcrud.security.EmployeeOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
/*
 * Config class for configurating the social login security aspect.
 * TODO: verify and implement stateless version.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeOAuth2UserService customOAuth2Service;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/employee/home").permitAll()
                .antMatchers(HttpMethod.POST, "/api/employee/insertNewEmployee").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2Service)
                ;
    }


}

