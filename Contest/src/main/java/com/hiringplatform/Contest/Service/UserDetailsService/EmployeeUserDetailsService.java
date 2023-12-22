package com.hiringplatform.Contest.Service.UserDetailsService;

import com.hiringplatform.Contest.model.Employee;
import com.hiringplatform.Contest.repos.Employeerepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class EmployeeUserDetailsService implements UserDetailsService {

    private final Employeerepository employeerepo;

    @Autowired
    public EmployeeUserDetailsService(Employeerepository employeerepo) {
        this.employeerepo = employeerepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeerepo.findByEmail(username);
        log.info(employee.toString());
        if(employee == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + employee.getRole()));
        return User.withUsername(employee.getEmail())
                .password(employee.getPassword())
                .authorities(authorities)
                .accountExpired(true)
                .accountLocked(true)
                .credentialsExpired(true)
                .disabled(true)
                .build();
    }
}
