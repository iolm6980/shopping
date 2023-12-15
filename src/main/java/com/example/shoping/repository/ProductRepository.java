package com.example.shoping.repository;

import com.example.shoping.entity.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long> , QuerydslPredicateExecutor<Product> {
    List<Product> findByType(ProductType productType);
    Page<Product> findByLikeCountGreaterThan(int likeCount, Pageable pageable);

    @Query("select p, pi, avg(p.likeCount) from Product p " +
            "right outer join ProductImage pi on pi.product = p group by p")
    Page<Object[]> getProductList(Pageable pageable);

    @Query("select distinct p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p " +
            "where p.pno = :pno group by pi")
    List<Object[]> getProduct(Long pno);


}
