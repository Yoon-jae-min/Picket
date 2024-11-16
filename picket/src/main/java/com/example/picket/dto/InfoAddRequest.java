package com.example.picket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class InfoAddRequest {
    private String category;
    private String detailCategory;
    private String title;
    private MultipartFile infoMainImg;
    private MultipartFile carousel;
    private String date;
    private String place;
    private String price;
    private String runTime;
    private String ageGrade;
    private MultipartFile[] infoExplainImgArr;
    private String[] infoExplainSortArr;
}
