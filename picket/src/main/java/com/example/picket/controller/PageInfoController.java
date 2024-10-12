package com.example.picket.controller;

import com.example.picket.dto.PerformanceForm;
import com.example.picket.entity.Customer;
import com.example.picket.service.PerformanceService;
import com.example.picket.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageInfoController {
    private final PerformanceService performanceService;
    private final WishListService wishListService;

    /*concert_info*/
    @GetMapping("/concert/season")
    public String gotoseason(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("2024 SEASON TEAM 노지훈 in SEOUL");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/season";
    }

    @GetMapping("/concert/beautiful")
    public String gotobeautiful(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("뷰티풀 민트 라이프 2024");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/beautiful";
    }

    @GetMapping("/concert/lovesome")
    public String gotolovesome(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("2024 LOVESOME - 마음 방울 채집");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/lovesome";
    }

    @GetMapping("/concert/boys")
    public String gotoboys(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("Boys Like Girls Live in Seoul");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/boys";
    }

    @GetMapping("/concert/daysix")
    public String gotodaysix(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("DAY6 CONCERT〈Welcome to the Show〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/daysix";
    }

    @GetMapping("/concert/king")
    public String gotoking(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("King Gnu Asia Tour ‘THE GREATEST UNKNOWN’ in Seoul");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/king";
    }

    @GetMapping("/concert/glow")
    public String gotoglow(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("더 글로우 2024 (THE GLOW 2024)");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/glow";
    }

    @GetMapping("/concert/hipho")
    public String gotoworldtour(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("HIPHOPPLAYA FESTIVAL 2024");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Concert/hipho";
    }

    /*musical_info*/

    @GetMapping("/musical/musical_paris.info")
    public String paris(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("[울산] 2024 뮤지컬 노테르담 드 파리 한국어 버전");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/paris";
    }

    @GetMapping("/musical/musical_touching.info")
    public String touching(Model model, HttpServletRequest request){
        log.info("서블릿리퀘스트:"+request.toString());
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [낭만별곡]");
        log.info("낭만별곡"+performanceForm.toString());
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/touching";
    }

    @GetMapping("/musical/musical_dia.info")
    public String dia(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [디아길레프]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/디아길레프";
    }

    @GetMapping("/musical/musical_dear.info")
    public String dear(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [디어 에반 핸슨] （Dear Evan Hansen)");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/dear";
    }

    @GetMapping("/musical/musical_bear.info")
    public String bear(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [베어 더 뮤지컬] (bare the musical)");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/bear";
    }

    @GetMapping("/musical/musical_next.info")
    public String next(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [넥스트 투 노멀]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/next";
    }

    @GetMapping("/musical/musical_here.info")
    public String here(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [여기,피화당]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/here";
    }

    @GetMapping("/musical/musical_violin.info")
    public String violin(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("뮤지컬 [파가니니]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Musical/violin";
    }


    /*act_info*/
    @GetMapping("/act/musicalTheaterCarolInfo")
    public String musicalTheaterCarol(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("음악극 〈캐롤〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);


        return "/categories/Act/musicalTheaterCarol";
    }
    @GetMapping("/act/theaterBaskervilles")
    public String theaterBasterCarol(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 〈바스커빌 : 셜록홈즈 미스터리〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterBaskervilles";
    }
    @GetMapping("/act/theaterBlossom")
    public String theaterBlossom(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [벚꽃동산]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterBlossom";
    }
    @GetMapping("/act/theaterHandSpan")
    public String theaterHandSpan(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [한뼘사이]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterHandSpan";
    }

    @GetMapping("/act/theaterCloserInfo")
    public String theaterCloser(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 〈클로저〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterCloser";
    }

    @GetMapping("/act/theaterHangOverInfo")
    public String theaterHangOver(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [행오버]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterHangOver";
    }

    @GetMapping("/act/theaterHerzKlangInfo")
    public String theaterHerzKlang(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 〈헤르츠클란〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterHerzKlang";
    }

    @GetMapping("/act/theaterHeungSinSoInfo")
    public String theaterHeungSinSo(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [수상한 흥신소]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterHeungSinSo";
    }

    @GetMapping("/act/theaterInHellInfo")
    public String theaterInHell(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 〈지옥에서〉");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterInHell";
    }

    @GetMapping("/act/theaterPSpartnerInfo")
    public String theaterPSpartner(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [나의PS파트너]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterPSpartner";
    }

    @GetMapping("/act/theaterRainbowInfo")
    public String theaterRainbow(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("연극 [넓은 하늘의 무지개를 보면 내 마음은 춤춘다]");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Act/theaterRainbow";
    }

    /*classic_info*/

    /* 라 트라비아타 이동 */
    @GetMapping("/classic/LaTraviata")
    public String gotoLaTraviata(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("라 트라비아타 - 춘희");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/LaTraviata";
    }

    /* 막심 벤게로프 이동 */
    @GetMapping("/classic/Maxim")
    public String gotoMaxim(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("막심 벤게로프 바이올린 리사이틀");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Maxim";
    }

    /* 백조의 호수 이동 */
    @GetMapping("/classic/Swan")
    public String gotoSwan(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("국립발레단 - 백조의 호수");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Swan";
    }

    /* 존 윌리엄스 이동 */
    @GetMapping("/classic/JohnWilliams")
    public String gotoJohnWilliams(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("국립심포니오케스트라 - 존 윌리엄스 시네마 콘서트");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/JohnWilliams";
    }

    /* 한여름밤의 꿈 이동 */
    @GetMapping("/classic/MidsummerNight")
    public String gotoMidsummerNight(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("국립오페라단 - 한여름 밤의 꿈");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/MidsummerNight";
    }

    /* 드뷔시 이동 */
    @GetMapping("/classic/Debussy")
    public String gotoDebussy(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("클래식 디깅 클럽 - 드뷔시");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Debussy";
    }

    /* 지브리 이동 */
    @GetMapping("/classic/GhiBli")
    public String gotoGhiBli(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("지브리 '&' 히사이시 조 : 디오케스트라 - 부천");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/GhiBli";
    }

    /*띵훈좌 이동 */
    @GetMapping("/classic/Jung")
    public String gotoJung(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("정명훈'&'도쿄필하모닉 내한공연");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Jung";
    }

    /* 해리포터 이동 */
    @GetMapping("/classic/Harry")
    public String gotoHarry(Model model, HttpServletRequest request) {
        PerformanceForm performanceForm = performanceService.findInfo("해리 포터와 불사조 기사단™ 인 콘서트");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Harry";
    }

    /* 상단 일무 이동*/
    @GetMapping("/classic/OneMu")
    public String gotoOneMu(Model model, HttpServletRequest request) {

        PerformanceForm performanceForm = performanceService.findInfo("일무");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/OneMu";
    }
    /* 상단 유키 이동 */
    @GetMapping("/classic/Yuki")
    public String gotoYuki(Model model, HttpServletRequest request) {

        PerformanceForm performanceForm = performanceService.findInfo("유키 구라모토 내한 25주년 기념 콘서트");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Classic/Yuki";
    }

    /*exhibit_info*/
    @GetMapping("/exhibit/banksy")
    public String getbanksy(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("리얼뱅크시 REAL BANKSY");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/banksy";
    }

    @GetMapping("/exhibit/ghibli")
    public String getghibli(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("[2차 얼리버드] 스튜디오 지브리 애니메이션의 거장 타카하타 이사오전");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/ghibli";
    }

    @GetMapping("/exhibit/graffiti")
    public String getgraffiti(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("그래피티 연금술사，시릴 콩고 Cyril Kongo´s");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/graffiti";
    }

    @GetMapping("/exhibit/india")
    public String getindia(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("스투파의 숲， 신비로운 인도 이야기");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/india";
    }

    @GetMapping("/exhibit/osaka")
    public String getosaka(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("후지시로 세이지 탄생 100주년 기념-오사카 파노라마展");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/osaka";
    }

    @GetMapping("/exhibit/seoul")
    public String getseoul(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("서울바앤스피릿쇼 2024");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/seoul";
    }

    @GetMapping("/exhibit/todusk")
    public String gettodusk(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("새벽부터 황혼까지 - 스웨덴 국립미술관 컬렉션");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/todusk";
    }

    @GetMapping("/exhibit/unity")
    public String getunity(Model model, HttpServletRequest request){
        PerformanceForm performanceForm = performanceService.findInfo("THE GREAT UNITY");
        performanceService.toModel(performanceForm, model);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        wishListService.WishListSelectIcon(performanceForm.getTitle(), (customer != null ? customer.getId() : null), model);

        return "/categories/Exhibit/unity";
    }

}
