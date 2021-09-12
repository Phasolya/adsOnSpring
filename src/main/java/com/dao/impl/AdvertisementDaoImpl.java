package com.dao.impl;

import com.dao.AdvertisementDao;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.entity.Advertisement;
import com.repository.AdvertisementRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdvertisementDaoImpl implements AdvertisementDao {

    final AdvertisementRepository ADS_REPOSITORY;

    @Override
    public void save(Advertisement advertisement) {
        ADS_REPOSITORY.save(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return ADS_REPOSITORY.findById(id).get();
    }

    @Override
    public void update(Advertisement advertisement) {
        ADS_REPOSITORY.save(advertisement);
    }

    @Override
    public void deleteById(int id) {
        ADS_REPOSITORY.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(ADS_REPOSITORY.count());
    }

    @Override
    public void deleteAllNotActive() {
        ADS_REPOSITORY.deleteAllByIsActiveFalse();
    }

    @Override
    public Page<Advertisement> getByCategoryDto(CategoryDto categoryDto) {

        int categoryId = categoryDto.getCategoryId();
        int startRow = categoryDto.getStartRow();
        int amount = categoryDto.getAmount();
        String sortDirection = categoryDto.getSortDirection();
        String sortParam = categoryDto.getSortParam();

        PageRequest pageReq
                = PageRequest.of(startRow, amount, Sort.Direction.fromString(sortDirection), sortParam);

        return ADS_REPOSITORY.getByCategoryId(categoryId, pageReq);

    }

    @Override
    public Page<Advertisement> getByCategoryHeaderDto(CategoryHeaderDto categoryHeaderDto) {

        int categoryId = categoryHeaderDto.getCategoryId();
        String header = categoryHeaderDto.getHeader();
        int startRow = categoryHeaderDto.getStartRow();
        int amount = categoryHeaderDto.getAmount();
        String sortDirection = categoryHeaderDto.getSortDirection();
        String sortParam = categoryHeaderDto.getSortParam();

        PageRequest pageReq
                = PageRequest.of(startRow, amount, Sort.Direction.fromString(sortDirection), sortParam);

        return ADS_REPOSITORY.getByCategoryIdAndHeader(categoryId, header, pageReq);
    }

    @Override
    public Page<Advertisement> getByCategoryHeaderPriceDto(CategoryHeaderPriceDto chpDto) {

        int categoryId = chpDto.getCategoryId();
        String header = chpDto.getHeader();
        BigDecimal priceFrom = chpDto.getPriceFrom();
        BigDecimal priceTo = chpDto.getPriceTo();
        int startRow = chpDto.getStartRow();
        int amount = chpDto.getAmount();
        String sortDirection = chpDto.getSortDirection();
        String sortParam = chpDto.getSortParam();

        PageRequest pageReq
                = PageRequest.of(startRow, amount, Sort.Direction.fromString(sortDirection), sortParam);

        return ADS_REPOSITORY.getByCategoryIdAndHeaderAndPriceBetween(categoryId, header, priceFrom, priceTo, pageReq);
    }
}
