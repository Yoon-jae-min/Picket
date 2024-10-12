package com.example.picket.service;


import com.example.picket.dto.AddCustomerRequest;
import com.example.picket.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.picket.entity.Customer;

import static java.lang.Long.parseLong;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(AddCustomerRequest dto){
        customerRepository.save(Customer.builder()
                .id(dto.getId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .tel(dto.getTel())
                .email(dto.getEmail())
                .name(dto.getName())
                .birthdate(dto.getBirthdate())
                .point(dto.getPoint())
                .build());
    }

    @Transactional
    public void edit(AddCustomerRequest dto, String id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null){;
            customer.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setTel(dto.getTel());
            customer.setBirthdate(dto.getBirthdate());
            customerRepository.save(customer);
        }
    }

    public boolean authentication(String id, String password) {
        String encodedPassword = customerRepository.findById(id).orElse(null).getPassword();
        log.info("id: "+id);
        log.info("password: "+password);
        log.info("encodedPassword: "+encodedPassword);
        if(customerRepository.findById(id).orElse(null).getId() != null){
            if(password == null){
                return false;
            }
            if(bCryptPasswordEncoder.matches(password, encodedPassword)){
                return true;
            }
        }
                return false;
    }

    public String findId(String name, String tel){
        Customer customer = customerRepository.findByTel(tel).orElse(null);
        if(customer != null && customer.getName().equals(name)){
            return customer.getId();
        }
        return "입력된 정보가 일치하지 않습니다. 다시 입력해주세요.";
    }
    public String findPW(String name, String tel, String id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null && customer.getName().equals(name) && customer.getTel().equals(tel)){
            return customer.getName();
        }
        else{
            return "입력된 정보가 일치하지 않습니다. 다시 입력해주세요.";
        }
    }
    @Transactional
    public boolean ChangePW(String id, String changePW){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null){
            customer.setPassword(bCryptPasswordEncoder.encode(changePW));
            customerRepository.save(customer);
            return true;
        }
        return false;
    }
    public String getReferer(HttpServletRequest request){
        String referer = request.getHeader("Referer");
        return referer;
    }

    @Transactional
    public void pointUpdate(String updatePoint, String customerId){
        customerRepository.updatePoint(parseLong(updatePoint), customerId);
    }

    public Long getPoint(String customerId){
        return customerRepository.findById(customerId).orElse(null).getPoint();
    }
}
