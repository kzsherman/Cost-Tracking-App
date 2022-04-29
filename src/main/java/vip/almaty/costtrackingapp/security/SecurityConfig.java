package vip.almaty.costtrackingapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private  final UserDetailsServiceImpl userDetailsService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/*","/css/*").permitAll()
                .antMatchers("/register").permitAll()
  //        .antMatchers("/admin/**").hasRole("ADMIN") // commented out on purpose, just for learning purposes
                .antMatchers("/**").hasRole("USER").and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/budgets")
                .permitAll().and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();

    }
}
