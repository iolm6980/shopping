package com.example.shoping.RepositoryTests;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.dto.PageResultDTO;
import com.example.shoping.dto.ProductDTO;
import com.example.shoping.dto.ProductImageDTO;
import com.example.shoping.entity.*;
import com.example.shoping.repository.ProductRepository;
import com.example.shoping.service.ProductService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductRepositoryTests {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    public void InsertProduct(){
        IntStream.rangeClosed(1,200).forEach(i->{
            Seller seller = Seller.builder().sno((long)(Math.random()*10)+1).build();
            Product product = Product.builder()
                    .name("testProduct"+i)
                    .price((int) (Math.random()*10000))
                    .likeCount((int) (Math.random()*50))
                    .type(ProductType.randomType())
                    .seller(seller)
                    .build();
            productRepository.save(product);
        });
    }

    @Test
    public void getProductList(){
        List<Product> productList = productRepository.findAll();

        System.out.println(productList);
    }
    @Test
    public void getProductByType(){
        List<Product> productList = productRepository.findByType(ProductType.PANTS);
        System.out.println(productList);
    }


    @Test
    public void QueryDslTest(){
//        QProduct product = QProduct.product;
//        List<Object> q1 = jpaQueryFactory.selectFrom(product).fetch();
//        List<Product> q2 = jpaQueryFactory.selectFrom(product).where(product.type.eq(ProductType.PANTS)).fetch();
//        List<Product> q3 = jpaQueryFactory.selectFrom(product).where(product.type.eq(ProductType.PANTS)).orderBy(product.price.desc()).fetch();
//        System.out.println(q3);
    }


    @Test
    public void modifyProduct(){
        Optional<Product> result = productRepository.findById(1L);
        result.get().changeLikeCount(0);
        result.get().changePrice(0);
        productRepository.save(result.get());
    }

    @Test
    public void testGetProductList(){
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<Object[]> result = productRepository.getProductList(pageRequest);
        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }


    }

    @Test
    public void testGetProduct(){
        Object result = productRepository.getProduct(2L);
    }
    ProductDTO entityToDTO(Product product, List<ProductImage> productImageList){
        List<ProductImageDTO> productImageDTO = productImageList.stream().map(productImage -> {
            return ProductImageDTO.builder()
                    .uuid(productImage.getUuid())
                    .path(productImage.getPath())
                    .imgName(productImage.getImgName())
                    .build();
        }).collect(Collectors.toList());

        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .name(product.getName())
                .price(product.getPrice())
                .likeCount(product.getLikeCount())
                .type(product.getType())
                .productImageList(productImageDTO)
                .build();

        return productDTO;
    }
}
