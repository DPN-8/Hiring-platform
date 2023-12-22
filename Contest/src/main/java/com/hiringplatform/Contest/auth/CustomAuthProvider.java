package com.hiringplatform.Contest.auth;

import com.hiringplatform.Contest.Service.UserDetailsService.EmployeeUserDetailsService;
import com.hiringplatform.Contest.Service.UserDetailsService.GuestUserDetailsService;

import com.hiringplatform.Contest.repos.Employeerepository;
import com.hiringplatform.Contest.repos.Guestrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthProvider implements AuthenticationProvider {


    @Autowired
    Guestrepository guestrepo;
    @Autowired
    Employeerepository employeerepo;

    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;
    @Autowired
    private GuestUserDetailsService guestUserDetailsService;

    public PasswordEncoder passwordEncoder;

    public CustomAuthProvider(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetailsService userDetailsService = determineUserDetailsService(email);
        UserDetails user = userDetailsService.loadUserByUsername(email);
        System.out.println(user.getPassword());
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UserDetailsService determineUserDetailsService(String email) {
        if (guestrepo.findByEmail(email) != null) {
            log.info("A guest is trying to login with the email id " + email);
            return guestUserDetailsService;
        } else if (employeerepo.findByEmail(email) != null) {
            log.info("A employee is trying to login with the email id " + email);
            return employeeUserDetailsService ;
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

}
