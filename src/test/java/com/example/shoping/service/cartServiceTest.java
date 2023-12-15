package com.example.shoping.service;

import com.example.shoping.dto.CartDTO;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.security.dto.AuthMemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class cartServiceTest {
    @Autowired
    private CartService cartService;

    @Test
    public void getCartService(){
        List<Long> cnoList = Arrays.asList(20L,21L,22L);

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.USER));
        List<CartDTO> list = cartService.getBuyList(new AuthMemberDTO("testId1", "1111", roles), cnoList);
        System.out.println(list);
    }
}
