package com.example.picket.repository;

import com.example.picket.entity.QA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;


public interface QARepository extends JpaRepository<QA, Long> {
    /* 날짜정렬 */
    @Query("SELECT qa FROM QA qa WHERE qa.customer.id IN :customerIds ORDER BY qa.write_date")
    List<QA> findAllByCustomerIds(@Param("customerIds") List<String> customerIds);
    @Query("SELECT qa FROM QA qa WHERE qa.customer.id IN : customerIds ORDER BY qa.write_date DESC")
    List<QA> findAllByCustomerIdsOrderByDesc(@Param("customerIds") List<String> customerIds);
}
