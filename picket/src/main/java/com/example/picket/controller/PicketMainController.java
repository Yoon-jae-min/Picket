package com.example.picket.controller;

import com.example.picket.entity.Customer;
import com.example.picket.service.PerformanceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PicketMainController {
    final private PerformanceService performanceService;

    /* 메인으로 이동 */
    @GetMapping("/main")
    public String gotoMain() { return "main"; }

    /* 고객센터로 이동 */
    @GetMapping("/support")
    public String supportMain() { return "/support/support";}

    /* 카테고리별 작품목록으로 이동 */
    @GetMapping("/categories/concertlist")
    public String concertList(Model model) {
        model.addAttribute("performanceList", performanceService.findList("concert"));
        return "/categories/List";
    }
    @GetMapping("/categories/musicallist")
    public String musicalList(Model model) {
        model.addAttribute("performanceList", performanceService.findList("musical"));
        return "/categories/List";
    }
    @GetMapping("/categories/actlist")
    public String actList(Model model) {
        model.addAttribute("performanceList", performanceService.findList("act"));
        return "/categories/List";
    }
    @GetMapping("/categories/classiclist")
    public String classicList(Model model) {
        model.addAttribute("performanceList", performanceService.findList("classic"));
        return "/categories/List";
    }
    @GetMapping("/categories/exhibitlist")
    public String exhibitList(Model model) {
        model.addAttribute("performanceList", performanceService.findList("exhibit"));
        return "/categories/List";
    }

    @PostMapping("/performInfo")
    public String performInfoReq(){
        return "redirect:/performInfo";
    }

    @GetMapping("/performInfo")
    public String performInfoRes(){
        return "/categories/Info";
    }

}
