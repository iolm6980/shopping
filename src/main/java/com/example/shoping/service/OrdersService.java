package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.security.dto.AuthMemberDTO;

import java.util.List;
public interface OrdersService {
    OrdersDTO createOrder(AuthMemberDTO authMemberDTO, List<CartDTO> cartList);
//    public void addOrder(OrdersDTO ordersDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO);
//
//    //public PageResultDTO<OrdersDTO, Object[]> getOrderList(String userId, PageRequestDTO pageRequestDTO);
//    public List<OrdersDTO> getOrderList(String userId);
//
//    public void deleteOrder(List<Long> onoList);
//
//    public void updateBuy(List<OrdersDTO> OrderList, Boolean bool);
//
//    public List<OrdersDTO> getBuyList(AuthMemberDTO authMemberDTO, List<Long> onoList);

    default Orders dtoToEntity(OrdersDTO ordersDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO){
        Member member = Member.builder().userId(authMemberDTO.getUsername()).build();
        Product product = Product.builder().pno(productDTO.getPno()).build();

        Orders orders = Orders.builder()
                .build();
        return orders;
    }

    default Cart dtoToEntity(CartDTO cartDTO){
        Product product = dtoToEntity()
        Cart cart = Cart.builder()
                .cno(cartDTO.getCno())
                .amount(cartDTO.getAmount())
                .size(cartDTO.getSize())
                .product()
                .
                .build();
    }


    default OrdersDTO entityToDTO(Orders orders, Product product){
        ProductDTO productDTO = entityToDTO(product);
        OrdersDTO ordersDTO = OrdersDTO.builder()
                .ono(orders.getOno())

                .build();
        return ordersDTO;
    }

    default OrdersDTO entityToDTO(Orders orders, Product product, Member member){
        ProductDTO productDTO = entityToDTO(product);
        MemberDTO memberDTO = MemberDTO.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .addressNum(member.getAddressNum())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .number(member.getNumber())
                .build();

        OrdersDTO ordersDTO = OrdersDTO.builder()
                .ono(orders.getOno())

                .memberDTO(memberDTO)
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
