package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.repository.MemberRepository;
import com.example.shoping.repository.OrdersRepository;
import com.example.shoping.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class OrdersServiceImpl implements OrdersService{
    private final OrdersRepository ordersRepository;
    @Override
    public void addOrder(OrdersDTO ordersDTO, ProductDTO productDTO, AuthMemberDTO authMemberDTO, SellerDTO sellerDTO) {
        Orders orders = dtoToEntity(ordersDTO, productDTO, authMemberDTO, sellerDTO);
        System.out.println("orders...."+ orders.getSeller());

        ordersRepository.save(orders);
    }

    @Override
    public List<OrdersDTO> getOrderList(String userId) {
        List<Object[]> ordersList = ordersRepository.orderList(userId);
        List<OrdersDTO> result = ordersList.stream().map(arr -> entityToDTO((Orders)arr[0], (Product)arr[1])).collect(Collectors.toList());
        return result;
    }

//    @Override
//    public PageResultDTO<OrdersDTO, Object[]> getOrderList(String userId ,PageRequestDTO pageRequestDTO) {
//        List<Object[]> productList = ordersRepository.orderList(userId);
//        Function<Object[], OrdersDTO> fn = (arr -> entityToDTO((Orders)arr[0], (Product)arr[1]));
//
//        int start = (pageRequestDTO.getPage()-1) * pageRequestDTO.getSize();
//        int end = start + pageRequestDTO.getSize() > productList.size() ? productList.size() : start + pageRequestDTO.getSize();
//        Page<Object[]> page = new PageImpl<>(productList.subList(start, end), pageRequestDTO.getPageable(), productList.size());
//
//        return new PageResultDTO<>(page, fn);
//    }

    @Override
    public void deleteOrder(List<Long> ono) {
        ordersRepository.deleteAllById(ono);
    }
}
