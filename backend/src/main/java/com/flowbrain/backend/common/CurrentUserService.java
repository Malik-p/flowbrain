package com.flowbrain.backend.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flowbrain.backend.user.entity.User;

@Service
public class CurrentUserService {

    public User getCurrentUser() {

        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}