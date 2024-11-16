package com.example.picket.controller;

import com.example.picket.dto.InfoResponse;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.entity.Customer;
import com.example.picket.service.PerformanceService;
import com.example.picket.service.WishListService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PicketMainController {
    final private PerformanceService performanceService;
    final private WishListService wishListService;

    /* 메인으로 이동 */
    @GetMapping("/main")
    public String gotoMain() { return "main"; }

    /* 고객센터로 이동 */
    @GetMapping("/support")
    public String supportMain() { return "/support/support";}

    /* 카테고리별 작품목록으로 이동 */
    @GetMapping("/categories/concertlist")
    public String concertList(Model model) {
        List<PerformanceForm> performanceFormList = performanceService.findList("concert");
        model.addAttribute("performanceList", performanceFormList);
        model.addAttribute("carousels", performanceService.RandCarousel(performanceFormList));
        model.addAttribute("category", "concert");
        return "/categories/List";
    }
    @GetMapping("/categories/musicallist")
    public String musicalList(Model model) {
        List<PerformanceForm> performanceFormList = performanceService.findList("musical");
        model.addAttribute("performanceList", performanceFormList);
        model.addAttribute("carousels", performanceService.RandCarousel(performanceFormList));
        model.addAttribute("category", "musical");
        return "/categories/List";
    }
    @GetMapping("/categories/actlist")
    public String actList(Model model) {
        List<PerformanceForm> performanceFormList = performanceService.findList("act");
        model.addAttribute("performanceList", performanceFormList);
        model.addAttribute("carousels", performanceService.RandCarousel(performanceFormList));
        model.addAttribute("category", "act");
        return "/categories/List";
    }
    @GetMapping("/categories/classiclist")
    public String classicList(Model model) {
        List<PerformanceForm> performanceFormList = performanceService.findList("classic");
        model.addAttribute("performanceList", performanceFormList);
        model.addAttribute("carousels", performanceService.RandCarousel(performanceFormList));
        model.addAttribute("category", "classic");
        return "/categories/List";
    }
    @GetMapping("/categories/exhibitlist")
    public String exhibitList(Model model) {
        List<PerformanceForm> performanceFormList = performanceService.findList("exhibit");
        model.addAttribute("performanceList", performanceFormList);
        model.addAttribute("carousels", performanceService.RandCarousel(performanceFormList));
        model.addAttribute("category", "exhibit");
        return "/categories/List";
    }

    //작품 상세정보 페이지
    @PostMapping("/performInfo")
    public String performInfoReq(@RequestBody Map<String, Object> requestValue, HttpSession session, Model model){
        List<InfoResponse> detailInfoNotice = performanceService.findInfoImg(requestValue.get("title").toString(), "notice");
        List<InfoResponse> detailInfoCasting = performanceService.findInfoImg(requestValue.get("title").toString(), "casting");
        List<InfoResponse> detailInfoDiscount = performanceService.findInfoImg(requestValue.get("title").toString(), "discount");
        List<InfoResponse> detailInfoSchedule = performanceService.findInfoImg(requestValue.get("title").toString(), "schedule");

        session.removeAttribute("detailInfoNotice");
        session.removeAttribute("detailInfoCasting");
        session.removeAttribute("detailInfoDiscount");
        session.removeAttribute("detailInfoSchedule");

        session.setAttribute("performanceInfo", performanceService.findInfo(requestValue.get("title").toString()));
        if(!detailInfoNotice.isEmpty()){
            session.setAttribute("detailInfoNotice", detailInfoNotice);
        }
        if(!detailInfoCasting.isEmpty()){
            session.setAttribute("detailInfoCasting", detailInfoCasting);
        }
        if(!detailInfoDiscount.isEmpty()){
            session.setAttribute("detailInfoDiscount", detailInfoDiscount);
        }
        if(!detailInfoSchedule.isEmpty()){
            session.setAttribute("detailInfoSchedule", detailInfoSchedule);
        }

        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(requestValue.get("title").toString(), (customer != null ? customer.getId() : null), model);

        session.setAttribute("imgUrl", model.getAttribute("imgUrl"));

        return "redirect:/performInfo";
    }

    @GetMapping("/performInfo")
    public String performInfoRes(){
        return "/categories/Info";
    }
}
