package cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/movies/**", "/cinema-halls/**",
                        "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/**", "/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/movies/**", "/cinema-halls/**", "/movie-sessions/**",
                        "/orders/**", "/shopping-carts/**").hasAnyRole("ADMIN", "USER")
                .and()
                .antMatcher("/register/**")
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
