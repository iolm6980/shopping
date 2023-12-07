package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.Seller;
import com.example.shoping.enums.MemberRole;
import com.example.shoping.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;
public interface OrdersService {
    public void addOrder(OrdersDTO ordersDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO, SellerDTO sellerDTO);
    
    //public PageResultDTO<OrdersDTO, Object[]> getOrderList(String userId, PageRequestDTO pageRequestDTO);
    public List<OrdersDTO> getOrderList(String userId);

    public void deleteOrder(List<Long> ono);

    default Orders dtoToEntity(OrdersDTO ordersDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO, SellerDTO sellerDTO){
        Seller seller = Seller.builder().sno(sellerDTO.getSno()).name(sellerDTO.getSName()).build();
        Member member = Member.builder().userId(authMemberDTO.getUsername()).build();
        Product product = Product.builder().pno(productDTO.getPno()).build();

        Orders orders = Orders.builder()
                .amount(ordersDTO.getAmount())
                .productSize(ordersDTO.getProductSize())
                .member(member)
                .product(product)
                .seller(seller)
                .build();
        return orders;
    }

    default OrdersDTO entityToDTO(Orders orders, Product product){
        ProductDTO productDTO = entityToDTO(product);
//        MemberDTO memberDTO = MemberDTO.builder()
//                .userId(orders.getMember().getUserId())
//                .email(orders.getMember().getEmail())
//                .nickName(orders.getMember().getNickName())
//                .address(orders.getMember().getAddress())
//                .number(orders.getMember().getNumber())
//                .build();

        OrdersDTO ordersDTO = OrdersDTO.builder()
                .ono(orders.getOno())
                .amount(orders.getAmount())
                .productSize(orders.getProductSize())
                .productDTO(productDTO)
                .build();
        return ordersDTO;
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
}
