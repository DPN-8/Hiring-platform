package com.hiringplatform.Contest.Service.UserDetailsService;


import com.hiringplatform.Contest.Controller.GuestController;
import com.hiringplatform.Contest.model.Guest;
import com.hiringplatform.Contest.model.Role;
import com.hiringplatform.Contest.repos.Guestrepository;
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
public class GuestUserDetailsService implements UserDetailsService {

    private final Guestrepository guestrepo;

    @Autowired
    public GuestUserDetailsService(Guestrepository guestrepo) {
        this.guestrepo = guestrepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Guest guest = guestrepo.findByEmail(username);
        System.out.println(username);

        if(guest == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.GUEST.name())); // Add the ROLE_Guest authority
        UserDetails userDetails = User.withUsername(guest.getEmail())
                .password(guest.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
        return userDetails;
    }
}
