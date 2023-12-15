package com.example.shoping.service;

import com.example.shoping.dto.OrdersDTO;
import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import com.example.shoping.entity.Orders;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.security.dto.AuthMemberDTO;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class OrdersServiceTests {
    @Autowired
    private OrdersService ordersService;

    @Test
    public void buyListTest(){
    }

    @Test
    public void deleteOrder(){
    }

    @Test
    public void orderListTest(){
    }

    @Test
    public void updateBuyTest(){
        List<Long> list = Arrays.asList(103L, 104L, 105L);
    }

    @Test
    public void getBuyList(){
//        List<Long> o = Arrays.asList(132L, 133L, 134L);
//        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//        roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.USER));
//        List<OrdersDTO> list = ordersService.getBuyList(new AuthMemberDTO("testId1", "1111", roles), o);
//        System.out.println(list);
    }
}
