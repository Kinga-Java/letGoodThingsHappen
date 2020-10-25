package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email, password, enabled FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role_name FROM user WHERE email = ?");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] staticResources = {
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/scripts/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/admin").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admiadmin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", false)
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .and()
                .httpBasic();
    }

}
