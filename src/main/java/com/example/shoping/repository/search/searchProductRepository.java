package com.example.shoping.repository.search;

import com.example.shoping.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface searchProductRepository {
    Page<Object[]> searchPage(PageRequestDTO pageRequestDTO);
}
