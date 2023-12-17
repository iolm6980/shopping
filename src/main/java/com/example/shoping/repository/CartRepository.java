package com.example.shoping.repository;

import com.example.shoping.entity.Cart;
import com.example.shoping.entity.Orders;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Transactional
    @Query("update Cart c set c.orders.ono = :ono where c.cno = :cno")
    void updateCartOrder(Long cno, Long ono);

    @Query("select c, c.product, c.member from Cart c where c.member.userId = :userId and c.orders IS NULL")
    List<Object[]> getCartListByUserId(String userId);
    @Query("select c, c.product from Cart c where c.member.userId = :userId and c.orders IS NOT NULL")
    List<Object[]> getBuyListByUserId(String userId);
    @Query("select c, c.product, c.member from Cart c where c.cno = :cno")
    List<Object[]> getCart(Long cno);

}
