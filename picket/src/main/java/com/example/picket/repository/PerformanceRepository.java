package com.example.picket.repository;

import com.example.picket.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, String> {

    @Query("select p from Performance p where p.category = :category")
    List<Performance> findByCategory(@Param("category") String category);

    @Query("select p from Performance p where p.title = :title")
    Performance findByInfo(@Param("title") String titleText);
}
