package com.example.picket.entity;

import com.example.picket.dto.InfoResponse;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class PerformanceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performInfoId")
    @SequenceGenerator(name = "performInfoId", sequenceName = "performInfoId_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column(nullable = false)
    private String performInfoUrl;

    @Column(nullable = false)
    private String sort;

    @Column(nullable = false)
    private int detailNum;

    public InfoResponse toInfoResponse() {
        return new InfoResponse(this.performInfoUrl, this.sort, this.detailNum);
    }
}
