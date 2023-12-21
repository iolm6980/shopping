package com.example.shoping.repository.search;

import com.example.shoping.dto.PageRequestDTO;
import com.example.shoping.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface searchProductRepository {
    Page<Object[]> searchPage(PageRequestDTO pageRequestDTO);
}
