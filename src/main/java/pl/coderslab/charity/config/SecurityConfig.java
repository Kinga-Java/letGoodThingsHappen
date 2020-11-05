package pl.coderslab.charity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final SuccesHandler succesHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email, password, active FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role FROM user WHERE email = ?");
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
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/**", "/swagger-ui.html", "/swagger-ui.html/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**").csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/createadmin").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/donation").authenticated()
                .antMatchers("/admin/institution/add").authenticated()
                .antMatchers("/").permitAll()
              /*  .antMatchers("/admin/home").hasRole("ADMIN")
                .antMatchers("/admin/institution/add").hasRole("ADMIN")
                .antMatchers("/admin/institution/list").hasRole("ADMIN")*/
//                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .successHandler(succesHandler)
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
