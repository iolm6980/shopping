package com.example.shoping.entity;

import com.example.shoping.dto.MemberDTO;
import com.example.shoping.enums.MemberRole;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Member extends BaseEntity{

    @Id
    private String userId;

    private String password;
    private String nickName;
    private String address;
    private String email;
    private String number;
    private MemberRole role;

}
