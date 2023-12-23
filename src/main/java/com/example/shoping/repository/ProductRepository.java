package com.example.shoping.repository;

import com.example.shoping.entity.*;
import com.example.shoping.repository.search.searchProductRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long> , searchProductRepository {
    Page<Product> findByLikeCountGreaterThan(int likeCount, Pageable pageable);

    @Query("select p, pi, avg(p.likeCount) from Product p " +
            "right outer join ProductImage pi on pi.product = p group by p")
    Page<Object[]> getProductList(BooleanBuilder booleanBuilder, Pageable pageable);

    @Query("select distinct p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p " +
            "where p.pno = :pno group by pi")
    List<Object[]> getProduct(Long pno);


}
