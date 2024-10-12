package com.example.picket.service;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Data
@Service
public class SessionService {

    public void saveSessionInformation(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Authentication getSessionInformation(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
