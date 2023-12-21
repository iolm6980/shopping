package com.example.shoping.controller;

import com.example.shoping.dto.ProductDTO;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Product;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class HomeController {
    private final ProductService productService;
    @GetMapping("/list")
    public void home(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMember){
        System.out.println("home--------------------");

        model.addAttribute("result", productService.getProductList(pageRequestDTO));
        model.addAttribute("auth", authMember);
    }

    @GetMapping("/detail")
    public void detail(Long pno ,@AuthenticationPrincipal AuthMemberDTO authMember, Model model){
        System.out.println(productService.getProduct(pno));
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

    @PostMapping("/remove/{pno}")
    public String removeProduct(@PathVariable("pno") Long pno){
        System.out.println("remove.........." + pno);
        productService.removeProduct(pno);
        return "redirect:/shopping/list";
    }

    @GetMapping("/modify")
    public void modify(ProductDTO productDTO, Model model){
        System.out.println("modify product..........." + productDTO);
        model.addAttribute("product", productDTO);
    }

    @PostMapping("/modify")
    public String postModify(ProductDTO productDTO){
        productService.modifyProduct(productDTO);
        return "redirect:/shopping/list";
    }


}
