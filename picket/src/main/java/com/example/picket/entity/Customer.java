package com.example.picket.entity;

import com.example.picket.dto.AddCustomerRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Customer implements UserDetails {
    @Id
    @Column(name = "customer_id")
    private String id;
    @Column(name = "customer_pw", nullable=false)
    private String password;
    @Column(name = "customer_email", nullable = false)
    private String email;
    @Column(name= "customer_name", nullable = false)
    private String name;
    @Column(name = "customer_birth", nullable = false)
    private String birthdate;
    @Column(name="customer_tel", unique = true, nullable = false)
    private String tel;
    @Column(name = "customer_point")
    private Long point;

    @Builder
    public Customer(String id, String password, String email, String name, String birthdate, String tel, Long point){
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.tel = tel;
        this.point = (point != null) ? point : 0L;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public String getUsername(){
        return id;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
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
