package com.example.picket.service;

import com.example.picket.dto.QAForm;
import com.example.picket.entity.Customer;
import com.example.picket.entity.QA;
import com.example.picket.repository.QARepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Slf4j
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application.properties")
public class QAService {

    private final QARepository qaRepository;

    @Value("${file.upload.dir}") // application.properties에서 설정한 디렉토리 경로
    private String fileDir;


    public void save(QAForm dto, HttpSession session) throws IOException {

        if(!dto.getFile().isEmpty()){
            Path filePath = Paths.get(fileDir + ((Customer) session.getAttribute("customer")).getId());
            boolean directoryExists = Files.exists(filePath) && Files.isDirectory(filePath);
            if (!directoryExists) {
                Files.createDirectories(filePath);
            }
            filePath = Paths.get(fileDir + ((Customer) session.getAttribute("customer")).getId() + File.separator + dto.getFile().getOriginalFilename());
            Files.copy(dto.getFile().getInputStream(), filePath);

            qaRepository.save(QA.builder()
                    .qa_title(dto.getQa_title())
                    .qa_content(dto.getQa_content())
                    .write_date(dto.getWrite_date())
                    .category(dto.getCategory())
                    .state(dto.getState())
                    .customer((Customer)session.getAttribute("customer"))
                    .filePath(fileDir + ((Customer) session.getAttribute("customer")).getId() + File.separator + dto.getFile().getOriginalFilename())
                    .build()
            );
        }
        else {
            qaRepository.save(QA.builder()
                    .qa_title(dto.getQa_title())
                    .qa_content(dto.getQa_content())
                    .write_date(dto.getWrite_date())
                    .category(dto.getCategory())
                    .state(dto.getState())
                    .customer((Customer)session.getAttribute("customer"))
                    .build()
            );
        }




    }

    @Transactional
    public List<QA> getAllQA(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        List<String> customerIds = Collections.singletonList(customer.getId());
        return qaRepository.findAllByCustomerIds(customerIds);
    }

    public QA getQADetailById(Long id) {
        return qaRepository.findById(id).orElse(null);
    }
}
