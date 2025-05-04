package com.rpfreire.AuthenticationService.service;

import com.rpfreire.AuthenticationService.dto.req.LogInReqDto;
import com.rpfreire.AuthenticationService.dto.req.SignInReqDto;
import com.rpfreire.AuthenticationService.dto.res.AuthResDto;
import com.rpfreire.AuthenticationService.entity.UserEntity;
import com.rpfreire.AuthenticationService.repository.RoleEntityRepository;
import com.rpfreire.AuthenticationService.repository.UserEntityRepository;
import com.rpfreire.AuthenticationService.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
        });
        user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).forEach(permission -> {
            authorityList.add(new SimpleGrantedAuthority(permission.getName()));
        });

        return new User(user.getUsername(), user.getPassword(), user.getIsEnabled(), user.getIsAccountNonExpired(), user.getIsCredentialsNonExpired(), user.getIsAccountNonLocked(), authorityList);
    }

    public AuthResDto createUser(SignInReqDto user) {
        String username = user.username();
        String password = passwordEncoder.encode(user.password());
        
        return null;
    }
    public AuthResDto loginUser(LogInReqDto user) {
        return null;
    }
    public Authentication authenticateUser(String username, String password) {
        return null;
    }



}
