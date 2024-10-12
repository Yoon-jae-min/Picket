package com.example.picket.config;


import com.example.picket.entity.Customer;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.service.CustomerDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Autowired
    private CustomerRepository customerRepository;
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/image/**")
                .requestMatchers("/font/**")
                .requestMatchers("홈피수정.mp4")
                .requestMatchers("/js/**")
                .requestMatchers("/fragments/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/QAWrite", "/QAList",
                                        "/mypagemain", "/profile", "/withdrawal", "/profileEdit", "/DeleteCustomer").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/loginpage")
                        .loginProcessingUrl("/login")
                        .usernameParameter("id")
                        .successHandler((request, response, authentication) ->{
                                User user = (User)authentication.getPrincipal();
                                Customer customer = customerRepository.findById(user.getUsername()).orElse(null);

                                if(customer != null){
                                    HttpSession session = request.getSession(true);
                                    session.setAttribute("customer", customer);
                                    session.setMaxInactiveInterval(1800);

                                    log.info("customer name: "+customer.getName()+"customer id:"+customer.getId());
                                        response.sendRedirect("/main");
                                }
                        })
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
                            response.sendRedirect("/loginpage");
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true)
                ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       CustomerDetailService customerDetailService)
            throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public  BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
