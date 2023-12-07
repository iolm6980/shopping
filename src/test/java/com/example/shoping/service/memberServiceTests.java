package com.example.shoping.service;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import com.example.shoping.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class memberServiceTests {
    @Value("${org.zerock.upload.path}")
    private String uploadPath;
    @Autowired
    private MemberService memberService;

    @Test
    public void printMyCart(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setSize(5);
        PageResultDTO result = memberService.getMyCart("testId1", pageRequestDTO);
        System.out.println();
        System.out.println("current page = " + result.getTotalPage());
        System.out.println();
        System.out.println(result.getPageList());
    }

    @Test
    public void cartTest(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        System.out.println(str);
        System.out.println(folderPath);
        File file = new File(uploadPath, folderPath);
        System.out.println(file);

        if(!file.exists()){
            file.mkdirs();
            System.out.println("폴더생성");
        }

    }





}
