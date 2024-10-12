package com.example.picket.controller;

import com.example.picket.dto.AddCustomerRequest;
import com.example.picket.dto.PaymentResponse;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.dto.WishListTitleRequest;
import com.example.picket.entity.*;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.repository.QARepository;
import com.example.picket.repository.TicketRepository;
import com.example.picket.repository.WishListRepository;
import com.example.picket.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProfileController {

    private  final WishListService wishListService;
    private  final PerformanceService performanceService;
    private  final PaymentService paymentService;
    private  final TicketService ticketService;

    private final QAService qaService;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private QARepository qaRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProfileService profileService;

    /* 마이페이지*/
    @GetMapping("/mypagemain")
    public String gotoMyPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");

        ticketService.ticketListModel(model, customer.getId());

        List<QA> qaList = qaService.getAllQA(session);
        model.addAttribute("qaList", qaList);

        List<WishList> wishListCount = wishListService.WishListFind(customer.getId());
        model.addAttribute("wishList", wishListCount);

        List<Payment> myTicketList = paymentService.findPaymentList(customer.getId());
        model.addAttribute("myTicketList", myTicketList);

        return "/mypage/mypagemain";
    }
    /* 찜목록 */
    @GetMapping("/wishlist")
    public String gotowishlist(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        List<QA> qaList = qaService.getAllQA(session);
        model.addAttribute("qaList", qaList);

        List<WishList> wishListCount = wishListService.WishListFind(customer.getId());
        model.addAttribute("wishList", wishListCount);

        /* 작동 테스트 (확인 완료)*/
        List<PerformanceForm> performances = new ArrayList<>();;
        List<WishList> wishLists = wishListService.WishListFind((customer != null ? customer.getId() : null));
        if(wishLists != null){
            for(WishList wishList: wishLists){
//                System.out.println("확인: " + performanceService.findInfo(wishList.getPerformance().getTitle()));
                performances.add(performanceService.findInfo(wishList.getPerformance().getTitle()));
            }
        }

        model.addAttribute("performances", performances);
        /* 작동 테스트 (확인 완료)*/

        List<Payment> myTicketList = paymentService.findPaymentList(customer.getId());
        model.addAttribute("myTicketList", myTicketList);

        return "/mypage/wishlist";
    }
    @PostMapping("/wishlist/delete")
    public ResponseEntity<Void> wishlistDelete(HttpServletRequest request, @RequestBody WishListTitleRequest wishListTitleRequest){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if(customer != null){
            String[] performanceTitles = wishListTitleRequest.getPerformanceTitles();

            if(performanceTitles != null){
                for(String performanceTitle: performanceTitles){
                    System.out.println("위시리스트 제목 = " + performanceTitle);
                    wishListService.wishListDelete(customer.getId(), performanceTitle);
                    System.out.println("반복");
                }
            }
        }

        return ResponseEntity.ok().build();
    }

    /* 회원정보 */
    @GetMapping("/profile")
    public String gotoProfile() { return "/mypage/profile"; }

    /* 회원정보 수정 */
    @PostMapping("/EditPasswordCheck")
    public String editPwCheck(String editPasswordCheck, HttpSession session, RedirectAttributes rttr) {
        if(profileService.passwordCheck(editPasswordCheck, session)){
            Customer customer = (Customer) session.getAttribute("customer");
         return "redirect:/profileEdit";
        }
        rttr.addFlashAttribute("passwordCheckError", "비밀번호가 일치하지 않습니다.");
        return "redirect:/profile";
    }
    @GetMapping("/profileEdit")
    public String gotoProfileEdit() { return "/mypage/profileEdit";}

    @PostMapping("/EditedProfile")
    public String updateProfile(AddCustomerRequest request, HttpSession session, RedirectAttributes rttr){
        Customer currentUser = (Customer) session.getAttribute("customer");
        String currentId = currentUser.getId();
        log.info("현재 유저ID:"+currentId);

        if(customerRepository.findByTel(request.getTel()).isEmpty() || request.getTel().equals(currentUser.getTel())){
            customerService.edit(request, currentId);
            session.invalidate();
            rttr.addFlashAttribute("Success","회원정보가 변경되었습니다. 다시 로그인해주세요.");
            return "redirect:/main";
        } else{
        rttr.addFlashAttribute("Error","이미 사용중인 전화번호입니다. 다시 입력해주세요.");
        return "redirect:/profileEdit";
        }
    }

    /* 회원탈퇴 */
    @GetMapping("/withdrawal")
    public String gotoWithdrawal() { return "/mypage/withdrawal";}

    @PostMapping("/DeletePasswordCheck")
    public String deletePwCheck(String deletePasswordCheck, HttpSession session, RedirectAttributes rttr) {
        if(profileService.passwordCheck(deletePasswordCheck, session)){
            return "redirect:/withdrawal";
        }
        rttr.addFlashAttribute("passwordCheckError", "비밀번호가 일치하지 않습니다.");
        return "redirect:/profile";
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(HttpSession session, RedirectAttributes rttr){
        Customer currentUser = (Customer) session.getAttribute("customer");
        if(currentUser != null){
            customerRepository.delete(currentUser);
            session.invalidate();
            rttr.addFlashAttribute("Success", "계정이 삭제되었습니다. 이용해주셔서 감사합니다.");
            return "redirect:/main";
        }
        rttr.addFlashAttribute("Error", "세션이 만료되었습니다. 다시 로그인한 뒤에 진행해주세요.");
        return "redirect:/main";
    }
}
