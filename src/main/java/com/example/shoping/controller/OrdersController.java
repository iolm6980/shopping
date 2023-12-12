package com.example.shoping.controller;

import com.example.shoping.dto.*;
import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    public String deleteOrder(@RequestParam List<Long> onoList){
        System.out.println(onoList);
        ordersService.deleteOrder(onoList);
        return "redirect:/orders/myOrderList";
    }

    @GetMapping("/buy")
    public void buy(@RequestParam List<Long> onoList , Model model, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
        List<OrdersDTO> list = ordersService.getBuyList(authMemberDTO, onoList);
        MemberDTO memberDTO = list.get(0).getMemberDTO();
        int totalPrice = 0;
        for(OrdersDTO order: list){
            totalPrice += order.getProductDTO().getPrice();
        }
        model.addAttribute("orderList", list);
        model.addAttribute("member", memberDTO);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("auth", authMemberDTO);
    }

    @PostMapping("/buy")
    public void postBuy(@RequestBody List<OrdersDTO> orderList){
        System.out.println("buy//////////////" + orderList);
        ordersService.updateBuy(orderList, true);
    }
}
