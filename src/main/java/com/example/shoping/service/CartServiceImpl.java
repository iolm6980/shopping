package com.example.shoping.service;

import com.example.shoping.dto.CartDTO;
import com.example.shoping.dto.OrdersDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.repository.CartRepository;
import com.example.shoping.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    @Override
    public void addCart(CartDTO cartDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO) {
        Cart cart = dtoToEntity(cartDTO, productDTO, authMemberDTO);
        cartRepository.save(cart);
    }
    @Override
    public List<CartDTO> getCartList(String userId) {
        List<Object[]> ordersList = cartRepository.getCartListByUserId(userId);
        List<CartDTO> result = ordersList.stream().map(arr -> entityToDTO((Cart) arr[0], (Product)arr[1], (Member)arr[2])).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CartDTO> getBuyList(String userId) {
        List<Object[]> ordersList = cartRepository.getBuyListByUserId(userId);
        List<CartDTO> result = ordersList.stream().map(arr -> entityToDTO((Cart) arr[0], (Product)arr[1])).collect(Collectors.toList());
        return result;
    }

    @Override
    public void deleteCart(List<Long> cnoList) {
        cartRepository.deleteAllById(cnoList);
    }

    @Override
    public void updateOrder(OrdersDTO ordersDTO) {
        List<Long> dtoList = ordersDTO.getCartList().stream().map(cartDTO -> cartDTO.getCno()).collect(Collectors.toList());
        List<Cart> list = cartRepository.findAllById(dtoList);
        list.forEach(cart ->{
            cartRepository.updateCartOrder(cart.getCno(), ordersDTO.getOno());
        });

    }

    @Override
    public List<CartDTO> getCheckList(AuthMemberDTO authMemberDTO, List<Long> cnoList) {
        List<Object[]> ordersList = new ArrayList<>();
        cnoList.forEach(cno ->{
            ordersList.addAll(cartRepository.getCart(cno));
        });
        List<CartDTO> result = ordersList.stream().map(arr -> entityToDTO((Cart)arr[0], (Product)arr[1], (Member)arr[2])).collect(Collectors.toList());
        return result;
    }
}
