package com.example.picket.controller;

import com.example.picket.dto.PerformanceForm;
import com.example.picket.service.PerformanceService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PerformanceService performanceService;
    private final HttpServletResponse httpServletResponse;
    private final HttpSession httpSession;

    @GetMapping("/adminList")
    public String adminList(Model model) {
        model.addAttribute("performanceList", performanceService.findListAll());
        return "/admin/adminListPage";
    }

    @PostMapping("/adminList")
    public ResponseEntity<List<PerformanceForm>> adminListRe(Model model, @RequestBody Map<String, Object> requestValue){
        return new ResponseEntity<>(performanceService.findList(requestValue.get("category").toString()), HttpStatus.OK);
    }

    @PostMapping("/adminInfo")
    public String adminInfo(@RequestBody Map<String, Object> requestValue, HttpSession session){
        session.setAttribute("performanceInfo", performanceService.findAdminInfo(requestValue.get("titleText").toString()));
        return "redirect:/adminInfo";
    }

    @GetMapping("/adminInfo")
    public String adminInfoTest(Model model, HttpSession session){
        model.addAttribute("performanceInfo", session.getAttribute("performanceInfo"));
        return "/admin/adminInfoPage";
    }
}
