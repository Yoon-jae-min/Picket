package com.example.picket.dto;

import com.example.picket.entity.Performance;
import com.example.picket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TicketForm {
    private String ticket_id;

    private Performance performance;

    public Ticket toEntity() { return new Ticket(ticket_id,performance);}
}
