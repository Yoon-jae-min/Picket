package com.example.picket.service;

import com.example.picket.dto.PerformanceForm;
import com.example.picket.entity.Performance;
import com.example.picket.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class  PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceForm findInfo(String title){
        log.info("들어온 제목"+title);
        Performance performance = performanceRepository.findById(title).orElse(null);
        log.info("현재 퍼포먼스:"+performance);
        return performance != null ? performance.toForm() : null;
    }

//    public Performance findInfoEnity(String title){
//        return performanceRepository.findById(title).orElse(null);
//    }


    public void toModel(PerformanceForm performanceForm, Model model){
        if(performanceForm != null){
        model.addAttribute("title", performanceForm.getTitle());
        model.addAttribute("place", performanceForm.getPlace());
        model.addAttribute("dates", performanceForm.getDates());
        model.addAttribute("category", performanceForm.getCategory());
        model.addAttribute("price", performanceForm.getPrice());
        }
    }

    public List<PerformanceForm> findList(String category){
        return performanceRepository.findByCategory(category).stream().map(Performance::toForm).collect(Collectors.toList());
    }

    public List<PerformanceForm> findListAll(){
        return performanceRepository.findAll().stream().map(Performance::toForm).collect(Collectors.toList());
    }

    public PerformanceForm findAdminInfo(String titleText){
        return performanceRepository.findByInfo(titleText).toForm();
    }
}
