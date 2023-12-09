package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import com.example.shoping.repository.OrdersRepository;
import com.example.shoping.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        List<Object[]> ordersList = ordersRepository.getMemberOrderList(userId);
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
    public void deleteOrder(List<Long> onoList) {
        ordersRepository.deleteAllById(onoList);
    }

    @Override
    @Transactional
    public void updateBuy(List<Long> onoList, Boolean bool) {
        List<Orders> list = ordersRepository.findAllById(onoList);
        list.forEach(orders -> {
            orders.changeBuy(bool);
        });
    }


    @Override
    public List<OrdersDTO> getBuyList(AuthMemberDTO authMemberDTO, List<Long> onoList) {
        List<Object[]> ordersList = new ArrayList<>();
        onoList.forEach(ono ->{
            ordersList.addAll(ordersRepository.getOrders(ono));
        });
        List<OrdersDTO> result = ordersList.stream().map(arr -> entityToDTO((Orders)arr[0], (Product)arr[1], (Member)arr[2])).collect(Collectors.toList());
        return result;
    }
}
