package com.dao;

import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.entity.Advertisement;
import com.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AdvertisementDao {

    void save(Advertisement advertisement);

    Advertisement findById(int id);

    void update(Advertisement advertisement);

    void deleteById(int id);

    int countAll();

    public void deleteAllNotActive();

    // ===============================================================================================================

    Page<Advertisement> getByCategoryDto(CategoryDto categoryDto);

    Page<Advertisement> getByCategoryHeaderDto(CategoryHeaderDto categoryHeaderDto);

    Page<Advertisement> getByCategoryHeaderPriceDto(CategoryHeaderPriceDto chpDto);

    // ===============================================================================================================

}
