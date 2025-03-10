package com.rpfreire.AuthenticationService.service;

import com.rpfreire.AuthenticationService.entity.UserEntity;
import com.rpfreire.AuthenticationService.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow(
                ()->{
                    throw new UsernameNotFoundException("User not found");
                }
        );
        List<SimpleGrantedAuthority>authorityList=new ArrayList<>();
        user.getRoles().forEach(role->{
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
        });
        user.getRoles().stream().flatMap(
                role -> role.getPermissions().stream())
                .forEach(
                        permission -> { authorityList.add(new SimpleGrantedAuthority(permission.getName()));
                });

        return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getIsEnabled(),
                    user.getIsAccountNonExpired(),
                    user.getIsCredentialsNonExpired(),
                    user.getIsAccountNonLocked(),
                authorityList);
    }



}
