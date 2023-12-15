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
    @Query("update Cart c set c.orders = :orders where c.cno = :cno")
    void updateCartOrder(Long cno, Orders orders);

    @Query("select c, c.product, c.member from Cart c where c.member.userId = :userId")
    List<Object[]> getCartListByUserId(String userId);

    @Query("select c, c.product, c.member from Cart c where c.cno = :cno")
    List<Object[]> getCart(Long cno);
}
