package vip.almaty.costtrackingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vip.almaty.costtrackingapp.security.UserDetailsServiceImpl;

@SpringBootApplication
public class CostTrackingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostTrackingAppApplication.class, args);
    }

    @Bean
    public SpringApplicationContext springApplicationContext () {
        return new SpringApplicationContext();
    }

//    @Bean(name = "AppProperties")
//    public AppProperties getAppProperties() {
//        return new AppProperties();
//    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
