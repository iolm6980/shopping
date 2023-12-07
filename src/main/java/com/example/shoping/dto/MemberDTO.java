package com.example.shoping.dto;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.ProductImage;
import com.example.shoping.enums.MemberRole;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class MemberDTO {
    private String userId;
    private String password;
    private String address;
    private String email;
    private String nickName;
    private String number;

}
