package com.example.shoping.controller;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Orders;
import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.service.OrdersService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/addOrder")
    public String addOrder(OrdersDTO ordersDTO, ProductDTO productDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO, SellerDTO sellerDTO){
        System.out.println("controller product"+authMemberDTO);
        System.out.println("controller product"+ordersDTO);
        System.out.println("controller product" +sellerDTO);
        ordersService.addOrder(ordersDTO, productDTO, authMemberDTO, sellerDTO);
        return "redirect:/shopping/list";
    }

    @GetMapping("/myOrderList")
    public void myOrderList(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){
        List<OrdersDTO> result = ordersService.getOrderList(authMemberDTO.getUsername());
        model.addAttribute("orderList", result);
        model.addAttribute("auth", authMemberDTO);
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam List<Long> productCheck){
        System.out.println(productCheck);
        ordersService.deleteOrder(productCheck);
        return "redirect:/orders/myOrderList";
    }

//    @GetMapping("/buy")
//    public void buy(@RequestParam List<Long> productCheck , Model model, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
//        System.out.println(productCheck);
//        model.addAttribute("productList", ordersService.getOrderList());
//        model.addAttribute("auth", authMemberDTO);
//    }
}
