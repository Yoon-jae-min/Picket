package com.example.picket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(WishCompositeKey.class)
public class WishList {
    @Id
    @ManyToOne
    @JoinColumn(name = "title", nullable = false)
    private Performance performance;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
}
