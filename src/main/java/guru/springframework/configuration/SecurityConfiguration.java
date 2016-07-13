package guru.springframework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
/**
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
    }
**/
@Autowired
DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username ,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login").logoutUrl("/j_spring_security_logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }
}