package com.example.shoping.repository.search;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.ProductType;
import com.example.shoping.entity.QProduct;
import com.example.shoping.entity.QProductImage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.stream.Collectors;
import java.util.List;
@Log4j2
public class searchProductRepositoryImpl extends QuerydslRepositorySupport implements searchProductRepository{
    public searchProductRepositoryImpl() {
        super(Product.class);
    }


    @Override
    public Page<Object[]> searchPage(PageRequestDTO pageRequestDTO) {
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<Product> jpqlQuery = from(product);
        jpqlQuery.rightJoin(productImage).on(productImage.product.eq(product));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(product, productImage, product.likeCount.avg());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(pageRequestDTO.getProductType() != null) booleanBuilder.and(product.type.eq(pageRequestDTO.getProductType()));
        if(pageRequestDTO.getKeyword() != null) booleanBuilder.and(product.name.contains(pageRequestDTO.getKeyword()));
        tuple.where(booleanBuilder);
        tuple.groupBy(product);
        this.getQuerydsl().applyPagination(pageRequestDTO.getPageable(),tuple);
        List<Tuple> result = tuple.fetch();
        Long count = tuple.fetchCount();
        return new PageImpl<Object[]>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList()),
                pageRequestDTO.getPageable(),
                count);
    }
}
