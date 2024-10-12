package com.example.picket.service;

import com.example.picket.dto.DeletePayContentForm;
import com.example.picket.dto.DeletePayRequestForm;
import com.example.picket.dto.DoPaymentForm;
import com.example.picket.entity.Customer;
import com.example.picket.entity.Payment;
import com.example.picket.entity.Ticket;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.repository.PaymentRepository;
import com.example.picket.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private PointService pointService;

    public void paymentInfoInput(List<Ticket> tickets, Customer customer, DoPaymentForm doPaymentForm){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREA);
        LocalDate performDate = LocalDate.parse(doPaymentForm.getPerformDate(), formatter);
        LocalDate payDate = LocalDate.parse(doPaymentForm.getPayDate(), formatter);


        for(Ticket ticket: tickets){
            paymentRepository.save(new Payment(ticket.getTicket_id(), customer, performDate, payDate, performDate.minusDays(5)));
        }
    }

    public List<Payment> findPaymentList(String customerId){
        return paymentRepository.customerPayment(customerId);
    }

    public Long deletePayment(String customerId, DeletePayRequestForm deletePayRequestForm){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREA);
        List<String> performTitles = new ArrayList<>();
        List<String> ticketIds = new ArrayList<>();
        Long totalRefundPrice = 0L;

        for(DeletePayContentForm deletePayContentForm: deletePayRequestForm.getPayTicketRequestBody()){
            if(!performTitles.contains(deletePayContentForm.getPerformTitle())){
                performTitles.add(deletePayContentForm.getPerformTitle());
            }
        }

        for(String performTitle: performTitles){
            for(Ticket ticket: ticketRepository.findByTitle(performTitle)){
                ticketIds.add(ticket.getTicket_id());
            }
        }

        for(String ticketId: ticketIds){
            Long performPrice = ticketRepository.findById(ticketId).orElse(null).getPerformance().getPrice();
            for(DeletePayContentForm deletePayContentForm: deletePayRequestForm.getPayTicketRequestBody()){
                if(paymentRepository.findDetail(customerId, ticketId, LocalDate.parse(deletePayContentForm.getPayDate(), formatter),
                        LocalDate.parse(deletePayContentForm.getPerformDate(), formatter)) != null){
                    paymentRepository.deleteById(ticketId);
                    ticketRepository.deleteById(ticketId);
                    customerRepository.updatePointPlus(performPrice, customerId);
                    totalRefundPrice += performPrice;
                }
            }
        }
        return totalRefundPrice;
    }
}
