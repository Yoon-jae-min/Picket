package com.example.picket.service;

import com.example.picket.entity.Customer;
import com.example.picket.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("입력된 id: "+id);
        Customer customer = customerRepository.findById(id)
                .orElse(null);
        if(customer!=null){
        log.info("가져온 Password"+customer.getPassword());
        log.info("id:"+customer.getId()+", password: "+customer.getPassword()+", authority: "+ customer.getAuthorities());
        return new User(customer.getId(), customer.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
        }
        return null;
    }
}
