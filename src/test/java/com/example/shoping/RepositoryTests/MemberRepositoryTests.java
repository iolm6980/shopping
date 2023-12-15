package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.*;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,30).forEach(i ->{
            Member member = Member.builder()
                    .userId("testId" + i)
                    .password(passwordEncoder.encode("1111"))
                    .email("testEmail"+i + "@naver.com")
                    .address("testAddress"+i)
                    .number("testNumber"+i)
                    .role(MemberRole.USER)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void findUser(){
        Optional<Member> member = memberRepository.findByUserId("testId1");
        if(member.isEmpty()) System.out.println("없음.....");
        else System.out.println(member.get());
    }



}
