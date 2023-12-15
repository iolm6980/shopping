package com.example.shoping.repository;

import com.example.shoping.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select o, o.cartList from Orders o")
    List<Object[]> getOrders();
//    @Query("select o, o.product, o.member from Orders o where o.member.userId = :userId and o.isBuy = 0")
//    List<Object[]> getMemberOrderList(String userId);
//
//    @Query("select o, o.product, o.member from Orders o where o.member.userId = :userId and o.isBuy = 1")
//    List<Object[]> getMemberBuyList(String userId);
//
//    @Query("select o, o.product, o.member from Orders o where o.ono = :ono")
//    List<Object[]> getOrders(Long ono);

}
