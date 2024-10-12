package com.example.picket.repository;

import com.example.picket.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query(value = "select * from payment where customer_id = :customerId",nativeQuery = true)
    List<Payment> customerPayment(String customerId);

    @Query(value = "select * from payment where customer_id = :customerId and ticket_id = :ticketId and pay_date = :payDate and perform_date = :performDate", nativeQuery = true)
    Payment findDetail(String customerId, String ticketId, LocalDate payDate, LocalDate performDate);
}
