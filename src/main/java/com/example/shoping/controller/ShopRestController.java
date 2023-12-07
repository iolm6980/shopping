package com.example.shoping.controller;

import com.example.shoping.dto.MemberDTO;
import com.example.shoping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ShopRestController {
    private final MemberService memberService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody MemberDTO memberDTO){
        System.out.println("memberRegister.............................."+ memberDTO);

        if(memberService.memberRegister(memberDTO)) return new ResponseEntity<>("회원가입성공", HttpStatus.OK);
        return new ResponseEntity<>("회원가입실패", HttpStatus.BAD_REQUEST);
    }

}
