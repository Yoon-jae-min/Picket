package com.example.picket.repository;

import com.example.picket.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Override
    Optional<Customer> findById(String id);
    Optional<Customer> findByTel(String tel);

    @Transactional
    @Modifying
    @Query(value = "update customer set customer_point = :updatePoint where customer_id = :customerId", nativeQuery = true)
    void updatePoint(Long updatePoint, String customerId);

    @Transactional
    @Modifying
    @Query(value = "update customer c set customer_point = c.customer_point + ?1 where customer_id = ?2", nativeQuery = true)
    void updatePointPlus(Long performPoint, String customerId);
}