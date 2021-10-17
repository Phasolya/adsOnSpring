package com.service.impl;


import com.domain.Role;
import com.domain.User;
import com.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link UserDetailsServiceImpl} class which is use for making work with
 * Spring Security and get Roles of Author.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserService USER_SERVICE;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = USER_SERVICE.findByLogin(username);

        List<GrantedAuthority> authorities = getGrantedAuthorities(user.getRoles());

        return buildUserDetails(user, authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles){

        return roles.stream()
                .map(roleClass -> new SimpleGrantedAuthority(roleClass.getName()))
                .collect(Collectors.toList());

    }

    private UserDetails buildUserDetails(User user, List<GrantedAuthority> authorities){

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);

    }

}
