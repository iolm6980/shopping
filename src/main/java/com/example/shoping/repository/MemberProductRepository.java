package com.example.shoping.repository;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.MemberProduct;
import com.example.shoping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MemberProductRepository extends JpaRepository<MemberProduct, Long> {
    @Query("select distinct p from MemberProduct mp left join Product p on mp.product.pno = p.pno where mp.member = :member")
    List<Product> getCartList(Member member);
}
