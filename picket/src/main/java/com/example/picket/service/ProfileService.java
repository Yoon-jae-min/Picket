package com.example.picket.service;

import com.example.picket.dto.AddCustomerRequest;
import com.example.picket.entity.Customer;
import com.example.picket.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private CustomerRepository customerRepository;


    public Boolean passwordCheck(String password, HttpSession session) {
        Customer currentUser = (Customer)session.getAttribute("customer");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(currentUser != null){
            String encodedPassword = currentUser.getPassword().toString();

            log.info("입력된 비밀번호: "+password+", 암호화된 비밀번호"+encodedPassword);
            if(passwordEncoder.matches(password, encodedPassword)) {
                return true;
            }
        }
        return false;
    }

    public void save(AddCustomerRequest dto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        customerRepository.save(Customer.builder()
                .id(dto.getId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .tel(dto.getTel())
                .birthdate(dto.getBirthdate())
                .email(dto.getEmail())
                .build());
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }
}
