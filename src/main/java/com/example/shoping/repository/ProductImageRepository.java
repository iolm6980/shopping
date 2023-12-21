package com.example.shoping.repository;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Modifying
    @Transactional
    @Query("delete from ProductImage pi where pi.product.pno = :pno")
    void deleteByPno(Long pno);
}
