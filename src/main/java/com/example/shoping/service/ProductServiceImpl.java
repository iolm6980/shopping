package com.example.shoping.service;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.dto.ProductImageDTO;
import com.example.shoping.entity.*;
import com.example.shoping.repository.ProductImageRepository;
import com.example.shoping.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final JPAQueryFactory jpaQueryFactory;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Override
    public PageResultDTO<ProductDTO, Object[]> getProductList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();
        //BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        Page<Object[]> result = productRepository.getProductList(pageable);
        Function<Object[], ProductDTO> fn = (arr -> entityToDTO((Product)arr[0], Arrays.asList((ProductImage) arr[1]), (Seller) arr[2]));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ProductDTO getProduct(Long pno) {
        List<Object[]> result = productRepository.getProduct(pno);
        Product product = (Product) result.get(0)[0];
        List<ProductImage> productImageList = new ArrayList<>();
        result.forEach(arr ->{
            ProductImage productImage = (ProductImage) arr[1];
            productImageList.add(productImage);
        });
        Seller seller = (Seller) result.get(0)[2];
        return entityToDTO(product, productImageList, seller);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        ProductType type = requestDTO.getProductType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(type != null) booleanBuilder.and(QProduct.product.type.eq(type));
        return booleanBuilder;
    }


}
