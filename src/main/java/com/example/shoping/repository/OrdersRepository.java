package com.example.shoping.repository;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.Orders;
import com.example.shoping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface OrdersRepository extends JpaRepository<Orders, Long> {
//    @Query("select o, o.product from Orders o where o.member.userId = :userId and o.isBuy = 0")
//    List<Object[]> orderList(String userId);
//
//    @Query("select o.member from Orders o where o.userId=:userId")
//    List<Member> memberInfo(String userId);

}
