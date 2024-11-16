package com.example.picket.repository;

import com.example.picket.entity.PerformanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceInfoRepository extends JpaRepository<PerformanceInfo, Integer> {
    @Query("SELECT pi FROM PerformanceInfo pi WHERE pi.performance.title = :title")
    List<PerformanceInfo> findAllByTitle(@Param("title") String title);

    @Modifying
    @Query("delete from PerformanceInfo pi where pi.performance.title = :title")
    void deleteAllByTitle(@Param("title") String title);
}
