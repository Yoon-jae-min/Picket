package com.example.picket.service;

import com.example.picket.entity.WishList;
import com.example.picket.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;

    public void WishListSave(WishList wishList){
        wishListRepository.save(wishList);
    }

    public void wishListDelete(String customer_id, String title){wishListRepository.deleteWish(customer_id, title); }

    public void WishListSelectIcon(String title, String customer_id, Model model){

        AtomicReference<Boolean> titleCheck = new AtomicReference<>(false);

        if(customer_id != null){
            List<WishList> wishLists = wishListRepository.findByAllCustomerId(customer_id);

            wishLists.forEach(wishList -> {
                if (wishList.getPerformance().getTitle().equals(title)) {
                    titleCheck.set(true);
                }
            });
        }

        if(titleCheck.get()){
            model.addAttribute("imgUrl", "../image/info/heart_red.png");
        }
        else {
            model.addAttribute("imgUrl", "../image/info/heart.png");
        }
    }

    @Transactional
    public List<WishList> WishListFind(String customer_id){
        List<WishList> wishLists = null;
        if(customer_id != null){
            wishLists = wishListRepository.findByAllCustomerId(customer_id);
        }

        return wishLists;
    }
}
