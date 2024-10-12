package com.example.picket.repository;

import com.example.picket.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    @Query("SELECT p FROM PointHistory p WHERE p.customer.id IN :customerIds ORDER BY p.changeDate")
    List<PointHistory> findAllByCustomerIds(@Param("customerIds") List<String> customerIds);

}
