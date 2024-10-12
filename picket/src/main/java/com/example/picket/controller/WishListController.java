package com.example.picket.controller;

import com.example.picket.dto.AddWishRequest;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.dto.WishListForm;
import com.example.picket.entity.Customer;
import com.example.picket.entity.Performance;
import com.example.picket.entity.WishList;
import com.example.picket.repository.PerformanceRepository;
import com.example.picket.repository.WishListRepository;
import com.example.picket.service.PerformanceService;
import com.example.picket.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final PerformanceService performanceService;

    @PostMapping("/wishEnroll")
    public String wishEnroll(HttpServletRequest request, @RequestBody AddWishRequest addWishRequest){

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if(customer != null){
            Performance performance = performanceService.findInfo(addWishRequest.getTitle()).toEntity();

            WishList wishList = new WishList();

            wishList.setCustomer(customer);
            wishList.setPerformance(performance);

            wishListService.WishListSave(wishList);
        }


        return "redirect:" + addWishRequest.getPath();
    }


    @PostMapping("/wishDelete")
    public String wishDelete(HttpServletRequest request, @RequestBody AddWishRequest addWishRequest) throws Exception {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        PerformanceForm performanceForm = performanceService.findInfo(addWishRequest.getTitle());

        if(customer != null){
            wishListService.wishListDelete(customer.getId(), performanceForm.getTitle());
        }

        return "redirect:" + addWishRequest.getPath();
    }
}
