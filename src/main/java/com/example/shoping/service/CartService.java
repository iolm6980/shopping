package com.example.shoping.service;

import com.example.shoping.dto.CartDTO;
import com.example.shoping.dto.MemberDTO;
import com.example.shoping.dto.OrdersDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.security.dto.AuthMemberDTO;
import java.util.List;
public interface CartService {
    public void addCart(CartDTO cartDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO);

//    //public PageResultDTO<OrdersDTO, Object[]> getOrderList(String userId, PageRequestDTO pageRequestDTO);
    public List<CartDTO> getCartList(String userId);
    public void deleteCart(List<Long> cnoList);
//
//    public void updateBuy(List<CartDTO> cartList, Boolean bool);
//
    public List<CartDTO> getBuyList(AuthMemberDTO authMemberDTO, List<Long> cnoList);

    default Cart dtoToEntity(CartDTO cartDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO){
        Member member = Member.builder().userId(authMemberDTO.getUsername()).build();
        Product product = Product.builder().pno(productDTO.getPno()).build();

        Cart cart = Cart.builder()
                .size(cartDTO.getSize())
                .amount(cartDTO.getAmount())
                .product(product)
                .member(member)
                .build();
        return cart;
    }


    default CartDTO entityToDTO(Cart cart, Product product){
        ProductDTO productDTO = entityToDTO(product);
        CartDTO cartDTO = CartDTO.builder()
                .cno(cart.getCno())
                .amount(cart.getAmount())
                .size(cart.getSize())
                .productDTO(productDTO)
                .build();
        return cartDTO;
    }

    default CartDTO entityToDTO(Cart cart, Product product, Member member){
        ProductDTO productDTO = entityToDTO(product);
        MemberDTO memberDTO = entityToDTO(member);

        CartDTO cartDTO = CartDTO.builder()
                .cno(cart.getCno())
                .amount(cart.getAmount())
                .size(cart.getSize())
                .productDTO(productDTO)
                .memberDTO(memberDTO)
                .build();
        return cartDTO;
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
    default MemberDTO entityToDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .addressNum(member.getAddressNum())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .number(member.getNumber())
                .build();
        return memberDTO;
    }
}
