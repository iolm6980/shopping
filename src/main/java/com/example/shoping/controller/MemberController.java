package com.example.shoping.controller;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.dto.MemberDTO;
import com.example.shoping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(MemberDTO memberDTO) {
        System.out.println("memberRegister.............................."+ memberDTO);
        return "redirect:/shopping/list";
//        if(memberService.memberRegister(memberDTO)) return new ResponseEntity<>("회원가입성공", HttpStatus.OK);
//        return new ResponseEntity<>("회원가입실패", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/modify")
    public void modify(Model model, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
        MemberDTO memberDTO = memberService.getMember(authMemberDTO.getUsername());
        System.out.println("modify............." + authMemberDTO);
        System.out.println("member............." + memberDTO);
        model.addAttribute("auth", authMemberDTO);
        model.addAttribute("member", memberDTO);
    }

    @PostMapping("/modify")
    public String postModify(MemberDTO memberDTO, Model model, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
        model.addAttribute("auth", authMemberDTO);
        System.out.println("post modify....." + memberDTO);
        memberService.modify(memberDTO);
        return "redirect:/member/modify";
    }


//    @PostMapping("/addCart")
//    public String addCart(ProductDTO productDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO){
//        System.out.println("controller 저장 성공............" + productDTO);
//        memberService.addMyCart(productDTO, authMemberDTO.getUsername());
//        return "redirect:/shopping/list";
//    }
//
//    @GetMapping("/myCartList")
//    public void myCartList(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model, PageRequestDTO pageRequestDTO){
//        pageRequestDTO.setSize(10);
//        model.addAttribute("cartPageResult", memberService.getMyCart(authMemberDTO.getUsername(), pageRequestDTO));
//        model.addAttribute("auth", authMemberDTO);
//    }

}
