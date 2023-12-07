package com.example.shoping.controller;

import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class HomeController {
    private final ProductService productService;
    @GetMapping("/list")
    public void home(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        System.out.println("home--------------------");
        System.out.println(authMember + "정보...............");
        model.addAttribute("result", productService.getProductList(pageRequestDTO));
        model.addAttribute("auth", authMember);
    }

    @GetMapping("/detail")
    public void detail(Long pno ,@AuthenticationPrincipal AuthMemberDTO authMember, Model model){
        model.addAttribute("auth", authMember);
        model.addAttribute("product", productService.getProduct(pno));
        System.out.println(authMember + "정보...............");
    }

    @GetMapping("/login")
    public String login(Model model,@RequestParam(required = false) String error, @RequestParam(required = false) String exception){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/shopping/login";
    }


}
