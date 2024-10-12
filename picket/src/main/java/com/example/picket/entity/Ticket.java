package com.example.picket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Ticket {
    @Id
    @Column(name="ticket_id")
    private String ticket_id;
    @ManyToOne
    @JoinColumn(name = "title", nullable = false)
    private Performance performance;
}
