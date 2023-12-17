package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.security.dto.AuthMemberDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface OrdersService {
    OrdersDTO createOrder(List<CartDTO> cartList);

    default Cart dtoToEntity(CartDTO cartDTO){
        Product product = Product.builder().pno(cartDTO.getProductDTO().getPno()).build();
        Member member = Member.builder().userId(cartDTO.getMemberDTO().getUserId()).build();
        Cart cart = Cart.builder()
                .cno(cartDTO.getCno())
                .amount(cartDTO.getAmount())
                .size(cartDTO.getSize())
                .product(product)
                .member(member)
                .build();
        return cart;
    }


    default OrdersDTO entityToDTO(Orders orders, Product product){
        ProductDTO productDTO = entityToDTO(product);
        OrdersDTO ordersDTO = OrdersDTO.builder()
                .ono(orders.getOno())
                .build();
        return ordersDTO;
    }

    default OrdersDTO entityToDTO(Orders orders){
        List<CartDTO> list = orders.getCartList().stream().map(cart ->entityToDTO(cart)).collect(Collectors.toList());
        OrdersDTO ordersDTO = OrdersDTO.builder()
                .ono(orders.getOno())
                .orderName(orders.getOrderName())
                .totalPrice(orders.getTotalPrice())
                .cartList(list)
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

    default CartDTO entityToDTO(Cart cart){
        ProductDTO productDTO = entityToDTO(cart.getProduct());
        CartDTO cartDTO = CartDTO.builder()
                .cno(cart.getCno())
                .amount(cart.getAmount())
                .size(cart.getSize())
                .productDTO(productDTO)
                .build();
        return cartDTO;
    }
}
