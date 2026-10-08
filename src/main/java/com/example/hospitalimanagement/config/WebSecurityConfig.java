package com.example.hospitalimanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//Spring ko batata hai ki yeh ek Configuration class hai.
public class WebSecurityConfig 
{

    @Bean
    public SecurityFilterChain  sfc(HttpSecurity https) throws Exception
       {
//security filter chain me hi httpsecurity hai oth2,addfilter,exceptionhandleing,csrf,sessionmgmt,loginform,headers,portmanager,logout,anonymous handling
 
        https.authorizeHttpRequests(authorizationManagerRequestMatcherRegister auth ->auth
        .requestMatchers("/public/**").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMAtcher("/doctor/**").hasRole("DOCTOR","ADMIN"))
        .formLogin(Customizer.withDefaults());
//https.formLogin(FormLoginConfigurer<HttpSecurity> formConfig ->formConfig. loginPage,paswordparameter,successForwardUrl,usernameParameter,
//equals,hashCode,authenticationDetails,defaultSuccessUrl,failureForwardUrl,permitAll,securityContextRepository,successHandler
//getClass,end,wait,notifyAll)
//u can configure login form but mostly companies have frontend have login form
//backend se apis jari hoti hai frontend me    
        return https.build();
//security filter chain
        }
//in memory user detail manager 
    @Bean
    UserDetailsService uds()
       {
       UserDetails user1 =  User.withUsername("admin")  //spring security ka core user
                                .password("admin")
                                .roles("admin")
                                .build();
        UserDetails user2 = User.withUsername("patient")
                                .password("patient1")
                                .roles("patient")
                                .build();
        return  new InMemoryUserDetailsManager(user1,user2);
        }


}
