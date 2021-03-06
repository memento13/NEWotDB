package pknu.otDB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/js/**", "/css/**","/rest/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/account/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select employee_id as username, CONCAT('{noop}',password) password, true enabled "
                        + "from EMPLOYEES "
                        + "where employee_id=?")
                .authoritiesByUsernameQuery("select employee_id as username, 'ROLE_ADMIN' authority "
                        + "from EMPLOYEES "
                        + "where employee_id = ?");
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}