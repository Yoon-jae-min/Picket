package com.example.picket.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_id")
    @SequenceGenerator(name="point_id", sequenceName = "point_id", initialValue = 1, allocationSize = 1)
    private Long pointId;

    @Column(name="point_type", nullable = false)
    private String pointType;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @Column(name = "points_change")
    private Long pointChange;

    @Column(name = "change_date")
    private Timestamp changeDate;

    @Builder
    public PointHistory(String pointType,Customer customer, Long pointChange, Timestamp changeDate){
        this.pointType = pointType;
        this.customer = customer;
        this.pointChange = pointChange;
        this.changeDate = (changeDate != null) ? changeDate : Timestamp.valueOf(LocalDateTime.now());
    }
}
