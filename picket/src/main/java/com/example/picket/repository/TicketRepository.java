package com.example.picket.repository;

import com.example.picket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(value = "select * from ticket where title = :performanceTitle", nativeQuery = true)
    List<Ticket> findByTitle(String performanceTitle);
}
