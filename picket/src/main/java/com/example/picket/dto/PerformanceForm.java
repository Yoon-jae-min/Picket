package com.example.picket.dto;

import com.example.picket.entity.Performance;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class PerformanceForm {
//    private Long id;
    private String title;
    private String place;
    private String dates;
    private String detailCategory;
    private Long price;

    //추가 변수
    private String imgUrl;
    private Long runtime;
    private String ageGrade;
    private String category;
    private String carouselImg;

    //사용 안함
    private String url;

    public Performance toEntity() {
        return new Performance(title,place,dates,detailCategory,price, imgUrl, carouselImg, runtime, ageGrade, category, url);}
}
