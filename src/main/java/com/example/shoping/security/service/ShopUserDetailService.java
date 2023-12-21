package com.example.shoping.security.service;

import com.example.shoping.security.dto.AuthMemberDTO;
import com.example.shoping.entity.Member;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class ShopUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUserId(username);
        if(member.isEmpty()) return null;
        Member result = member.get();
        System.out.println("---------------test--------------" + member);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        if(member.get().getRole() == MemberRole.ADMIN) roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.ADMIN));
        else roles.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.USER));
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                result.getUserId(),
                result.getPassword(),
                roles
        );

        return authMemberDTO;
    }
}
