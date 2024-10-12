package com.example.picket.service;

import com.example.picket.dto.PaymentResponse;
import com.example.picket.entity.Payment;
import com.example.picket.entity.Performance;
import com.example.picket.entity.Ticket;
import com.example.picket.repository.PerformanceRepository;
import com.example.picket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PerformanceRepository performanceRepository;
    private final PaymentService paymentService;

    private static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 사용할 문자들
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }

    public List<Ticket> findTicketId(String performanceTitle){
        return ticketRepository.findByTitle(performanceTitle);
    }

    public List<Ticket> ticketCreateInput(String ticketCount, String performanceTitle){
        Performance performance = performanceRepository.findById(performanceTitle).orElse(null);
        List<Ticket> tickets = new ArrayList<>();
        if(performance != null){
            for(int i = 0; i < parseInt(ticketCount); i++){
                String ticketId = generateRandomString(10);
                Ticket ticket = new Ticket(ticketId, performance);
                Ticket searchTicket = ticketRepository.findById(ticketId).orElse(null);

                if(searchTicket == null){
                    tickets.add(ticketRepository.save(ticket));
                }
                else {
                    i--;
                }
            }
        }
        return tickets;
    }

    public void ticketListModel(Model model, String customerId){
        List<Payment> paymentList = paymentService.findPaymentList(customerId);
        List<PaymentResponse> paymentResponseList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREA);
        boolean addCheck = true; //true = 추가, false = 숫자 업데이트

        if(paymentList != null){
            for(Payment payment: paymentList){
                Ticket ticket = ticketRepository.findById(payment.getTicket_id()).orElse(null);

                if(ticket != null){
                    for(PaymentResponse paymentResponse: paymentResponseList){
                        if(ticket.getPerformance().getTitle().equals(paymentResponse.getPerformanceTitle()) &&
                            payment.getPay_date().format(formatter).equals(paymentResponse.getPayDate()) &&
                            payment.getPerform_date().format(formatter).equals(paymentResponse.getPerformDate())){
                            paymentResponse.setTicketCount(Integer.toString(parseInt(paymentResponse.getTicketCount()) + 1));
                            paymentResponse.setTotalPrice(Long.toString(parseInt(paymentResponse.getTotalPrice()) + ticket.getPerformance().getPrice()));
                            addCheck = false;
                            break;
                        }
                        else {
                            addCheck = true;
                        }
                    }
                    if(addCheck){
                        paymentResponseList.add(new PaymentResponse(payment.getPay_date().format(formatter), ticket.getPerformance().getTitle(),
                                payment.getPerform_date().format(formatter), payment.getCancel_date().format(formatter),
                                "1", Long.toString(ticket.getPerformance().getPrice()), ticket.getPerformance().getUrl()));
                    }
                }
            }
            for(PaymentResponse paymentResponse: paymentResponseList){
                paymentResponse.setTicketCount(paymentResponse.getTicketCount() + "매");
            }
        }

        model.addAttribute("payTicketList", paymentResponseList);
    }
}
