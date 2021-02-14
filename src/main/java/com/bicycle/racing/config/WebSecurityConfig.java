package com.bicycle.racing.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Autowired
//    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
//        this.userDetailsServiceImpl = userDetailsServiceImpl;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll() //TODO
//                .antMatchers("/api/**").authenticated()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/api/login");
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder(16));
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
