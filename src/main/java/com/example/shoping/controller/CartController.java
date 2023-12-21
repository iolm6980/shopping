package com.example.shoping.controller;

import com.example.shoping.dto.CartDTO;
import com.example.shoping.dto.MemberDTO;
import com.example.shoping.dto.OrdersDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.entity.Orders;
import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/addOrder")
    public String addOrder(CartDTO cartDTO, ProductDTO productDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
        System.out.println("controller product"+cartDTO);
        cartService.addCart(cartDTO, productDTO, authMemberDTO);
        return "redirect:/shopping/list";
    }

    @GetMapping("/myCartList")
    public void myOrderList(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){
        List<CartDTO> result = cartService.getCartList(authMemberDTO.getUsername());
        System.out.println("cartList" + result);
        System.out.println("권한..............." + authMemberDTO.getAuthorities());
        model.addAttribute("cartList", result);
        model.addAttribute("auth", authMemberDTO);
    }

    @GetMapping("/myBuyList")
    public void myBuyList(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){
        List<CartDTO> result = cartService.getBuyList(authMemberDTO.getUsername());
        System.out.println("buyList---------" + result);
        model.addAttribute("buyList", result);
        model.addAttribute("auth", authMemberDTO);
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam List<Long> cnoList){
        System.out.println(cnoList);
        cartService.deleteCart(cnoList);
        return "redirect:/cart/myCartList";
    }

    @GetMapping("/buy")
    public void buy(@RequestParam List<Long> cnoList , Model model, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
        List<CartDTO> list = cartService.getCheckList(authMemberDTO, cnoList);
        MemberDTO memberDTO = list.get(0).getMemberDTO();
        int totalPrice = 0;
        for(CartDTO cartDTO: list){
            totalPrice += cartDTO.getProductDTO().getPrice();
        }
        model.addAttribute("cart", list);
        model.addAttribute("member", memberDTO);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("auth", authMemberDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrdersDTO ordersDTO){
        System.out.println("------------------- " + ordersDTO);
        cartService.updateOrder(ordersDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
