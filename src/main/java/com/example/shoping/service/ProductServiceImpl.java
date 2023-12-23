package com.example.shoping.service;

import com.example.shoping.dto.*;
import com.example.shoping.entity.*;
import com.example.shoping.repository.CartRepository;
import com.example.shoping.repository.ProductImageRepository;
import com.example.shoping.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CartRepository cartRepository;

    @Override
    public PageResultDTO<ProductDTO, Object[]> getProductList(PageRequestDTO pageRequestDTO) {
        Page<Object[]> result = productRepository.searchPage(pageRequestDTO);
        Function<Object[], ProductDTO> fn = (arr -> entityToDTO((Product)arr[0], Arrays.asList((ProductImage) arr[1])));

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
        return entityToDTO(product, productImageList);
    }

    @Override
    public void removeProduct(Long pno) {
        productImageRepository.deleteByPno(pno);
        cartRepository.deleteByPno(pno);
        productRepository.deleteById(pno);
    }

    @Override
    public void modifyProduct(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void registerProduct(ProductDTO productDTO, List<ProductImageDTO> uploadList) {
        Product product = productRepository.save(dtoToEntity(productDTO));
        List<ProductImage> list = uploadList.stream().map(img -> dtoToEntity(img, product)).collect(Collectors.toList());
        productImageRepository.saveAll(list);
    }


}
