package com.example.picket.controller;

import com.example.picket.dto.InfoAddRequest;
import com.example.picket.dto.InfoResponse;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.service.PerformanceService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PerformanceService performanceService;

    //관리자 페이지 공연 리스트 조회
    @GetMapping("/adminList")
    public String adminList(Model model, HttpSession session) {
        model.addAttribute("performanceList", performanceService.findListAll());
        return "/admin/adminListPage";
    }

    @PostMapping("/adminList")
    public ResponseEntity<List<PerformanceForm>> adminListRe(Model model, @RequestBody Map<String, Object> requestValue){
        if(requestValue.get("category").toString().equals("total")){
            return new ResponseEntity<>(performanceService.findListAll(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(performanceService.findList(requestValue.get("category").toString()), HttpStatus.OK);
        }
    }

    //관리자 공연 상제 정보 조회
    @PostMapping("/adminInfo")
    public String adminInfo(@RequestBody Map<String, Object> requestValue, HttpSession session){
        List<InfoResponse> detailInfoNotice = performanceService.findInfoImg(requestValue.get("titleText").toString(), "notice");
        List<InfoResponse> detailInfoCasting = performanceService.findInfoImg(requestValue.get("titleText").toString(), "casting");
        List<InfoResponse> detailInfoDiscount = performanceService.findInfoImg(requestValue.get("titleText").toString(), "discount");
        List<InfoResponse> detailInfoSchedule = performanceService.findInfoImg(requestValue.get("titleText").toString(), "schedule");

        session.removeAttribute("detailInfoNotice");
        session.removeAttribute("detailInfoCasting");
        session.removeAttribute("detailInfoDiscount");
        session.removeAttribute("detailInfoSchedule");

        session.setAttribute("performanceInfo", performanceService.findInfo(requestValue.get("titleText").toString()));
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

        return "redirect:/adminInfo";
    }

    @GetMapping("/adminInfo")
    public String adminInfoGet(){
        return "/admin/adminInfoPage";
    }

    //공연 추가 페이지
    @GetMapping("/adminWrite")
    public String adminWriteGet(){
        return "/admin/adminWritePage";
    }

    //공연 삭제
    @PostMapping("/deleteInfo")
    @ResponseBody
    public Map<String, String> adminDeleteInfo(@RequestBody Map<String, Object> requestValue){
        performanceService.deletePerformInfo(requestValue.get("title").toString());

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/adminList");

        return response;
    }

    //공연 추가
    @PostMapping("/addInfo")
    public String adminAddInfo(@RequestParam("category") String category,
                               @RequestParam("detailCategory") String detailCategory,
                               @RequestParam("title") String title,
                               @RequestParam("infoMainImg") MultipartFile infoMainImg,
                               @RequestParam("carousel") MultipartFile caroucel,
                               @RequestParam("date") String date,
                               @RequestParam("place") String place,
                               @RequestParam("price") String price,
                               @RequestParam("runTime") String runTime,
                               @RequestParam("ageGrade") String ageGrade,
                               @RequestParam("infoExplainImgArr") MultipartFile[] infoExplainImgArr,
                               @RequestParam("infoExplainSortArr") String[] infoExplainSortArr) throws IOException {
        System.out.println("테스트");
        InfoAddRequest infoAddRequest = new InfoAddRequest(category, detailCategory, title, infoMainImg, caroucel, date, place, price,
                runTime, ageGrade, infoExplainImgArr, infoExplainSortArr);

        performanceService.addPerformInfo(infoAddRequest);
        return "redirect:/adminList";
    }
}
