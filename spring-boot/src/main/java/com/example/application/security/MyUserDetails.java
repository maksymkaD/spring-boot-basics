package com.example.application.security;

import java.util.*;

import com.example.application.dal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority admin = new SimpleGrantedAuthority("admin");
        SimpleGrantedAuthority teacher = new SimpleGrantedAuthority("teacher");
        SimpleGrantedAuthority student = new SimpleGrantedAuthority("student");

        if (Objects.equals(this.user.getRole(), "teacher")) {
            authorities.add(teacher);
        }

        if (Objects.equals(this.user.getRole(), "student")) {
            authorities.add(student);
        }

        if (Objects.equals(this.user.getRole(), "admin")) {
            authorities.add(admin);
            authorities.add(teacher);
            authorities.add(student);
        }

        return authorities;
    }

    public Integer getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
