package com.example.picket.controller;

import com.example.picket.entity.Customer;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerViewController{
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;


    @GetMapping("/loginpage")
    public String loginpage(HttpSession session, Model model){
        String errorMessage = (String) session.getAttribute("errorMessage");
        if(errorMessage != null){
            log.info("로그인 실패: 아이디, 비밀번호 불일치");
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        return "/login/login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        log.info("request: "+request+", response: "+response+", authentication: "+SecurityContextHolder.getContext().getAuthentication());
        session.invalidate();
        log.info("세션 무력화 완료");
        return "redirect:/main";
    }
    @GetMapping("/FindIDPW")
    public String findIDPW(){ return "/login/FindIDPW"; }

    @PostMapping("/FindId")
    public String findId(String name, String tel, RedirectAttributes rttr){
        String foundId = customerService.findId(name, tel);
        if(foundId.equals("입력된 정보가 일치하지 않습니다. 다시 입력해주세요.")){
            rttr.addFlashAttribute("findIdError", foundId);
            return "redirect:/FindIDPW";
        }
        rttr.addFlashAttribute("foundId", foundId);
        rttr.addFlashAttribute("name", name);
        return "redirect:/FindIDPW";
    }

    @PostMapping("/FindPW")
    public String findPW(String name2, String tel2, String id2, RedirectAttributes rttr, HttpSession session){
        String userName = customerService.findPW(name2, tel2, id2);
        Customer customer = customerRepository.findById(id2).orElse(null);
        if(userName.equals("입력된 정보가 일치하지 않습니다. 다시 입력해주세요.")){
        rttr.addFlashAttribute("findPWError", userName);
        return "redirect:/FindIDPW";
        }
        else if(userName != null && customer.getName().equals(userName)){
            rttr.addFlashAttribute("changePW", true);
            session.setAttribute("id2", id2);
            log.info("changePW: "+rttr.getFlashAttributes());
            return "redirect:/FindIDPW";
        }
        else{
            rttr.addFlashAttribute("findPWError", "잘못 입력하셨습니다. 다시 입력해주세요.");
            return "redirect:/FindIDPW";
        }
    }

    @PostMapping("/ChangePW")
    public String changePW(String changedPW, String changedPWCheck,HttpServletRequest request,
                           RedirectAttributes rttr, HttpSession session){
        if(changedPW.equals(changedPWCheck)){
            String userId = session.getAttribute("id2").toString();
            if(userId != null){
            customerService.ChangePW(userId, changedPW);
            rttr.addFlashAttribute("successMessage", "비밀번호 변경이 완료되었습니다.");
            return "redirect:/loginpage";
            }
        } else{
            rttr.addFlashAttribute("findPWError", "입력한 비밀번호가 서로 다릅니다. 다시 확인해주세요.");
        }
            return "redirect:"+customerService.getReferer(request);
    }
}
