package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Member;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.Seller;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {
    boolean memberRegister(MemberDTO memberDTO);
    MemberDTO getMember(String uid);
    void modify(MemberDTO memberDTO);
    void addMyCart(ProductDTO productDTO, String userId);

    PageResultDTO<ProductDTO, Product> getMyCart(String uid, PageRequestDTO pageRequestDTO);
    default MemberDTO entityToDto(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .addressNum(member.getAddressNum())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .number(member.getNumber())
                .build();
        return memberDTO;
    }

    default Member dtoToEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .email(memberDTO.getEmail())
                .nickName(memberDTO.getNickName())
                .addressNum(memberDTO.getAddressNum())
                .address(memberDTO.getAddress())
                .detailAddress(memberDTO.getDetailAddress())
                .number(memberDTO.getNumber())
                .role(MemberRole.USER)
                .build();
        return member;
    }

    default Member dtoToEntity(MemberDTO memberDTO){
        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .nickName(memberDTO.getNickName())
                .addressNum(memberDTO.getAddressNum())
                .address(memberDTO.getAddress())
                .detailAddress(memberDTO.getDetailAddress())
                .number(memberDTO.getNumber())
                .role(MemberRole.USER)
                .build();
        return member;
    }

    default ProductDTO entityToDTO(Product product){
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .name(product.getName())
                .price(product.getPrice())
                .likeCount(product.getLikeCount())
                .type(product.getType())
                .build();
        return productDTO;
    }
    default Product dtoToEntity(ProductDTO productDTO){
        Seller seller = Seller.builder().sno(productDTO.getSellerDTO().getSno()).build();

        Product product = Product.builder()
                .pno(productDTO.getPno())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .likeCount(productDTO.getLikeCount())
                .type(productDTO.getType())
                .seller(seller)
                .build();
        return product;
    }

}
