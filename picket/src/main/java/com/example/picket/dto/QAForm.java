package com.example.picket.dto;

import com.example.picket.entity.Customer;
import com.example.picket.entity.QA;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class QAForm {
    private Long qa_id;
    private String qa_title;
    private String qa_content;
    private Timestamp write_date;
    private String category;
    private String state;
    private Customer customer;
    private MultipartFile file;
    
}
