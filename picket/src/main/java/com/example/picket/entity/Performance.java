package com.example.picket.entity;

import com.example.picket.dto.PerformanceForm;
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
public class Performance {
    @Id
    private String title;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private String dates; // 공연기간
    @Column(nullable = true)
    private String detailCategory;
    @Column(nullable = true)
    private Long price;

    //추가 컬럼
    @Column(nullable = true)
    private String imgUrl;
    @Column(nullable = true)
    private String carouselUrl;
    @Column(nullable = true)
    private Long runtime;
    @Column(nullable = true)
    private String ageGrade;
    @Column(nullable = false)
    private String category;

    //사용 안함
    @Column(nullable = true)
    private String url;

    public PerformanceForm toForm(){
        return new PerformanceForm(title, place, dates, detailCategory, price, imgUrl, runtime, ageGrade, category,
                carouselUrl , url);
    }
}
