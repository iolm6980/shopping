package com.example.shoping.RepositoryTests;

import com.example.shoping.entity.Member;
import com.example.shoping.entity.Product;
import com.example.shoping.entity.ProductImage;
import com.example.shoping.repository.ProductImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductImageRepositoryTests {
    @Value("${org.zerock.upload.path}")
    private String upload;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Test
    public void insertImage(){
        Long[] num = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L};
        IntStream.rangeClosed(0, 8).forEach(i ->{
            String folderPath = "https://picsum.photos/id/" + (i) + "/200/200";
            ProductImage img = ProductImage.builder()
                                .uuid(String.valueOf(i))
                                .path(folderPath)
                                .imgName("test"+i)
                                .product(Product.builder().pno(num[i]).build())
                                .build();
            productImageRepository.save(img);
        });
    }

}
