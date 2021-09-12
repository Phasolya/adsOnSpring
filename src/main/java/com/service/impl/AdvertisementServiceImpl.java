package com.service.impl;

import com.dao.AdvertisementDao;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.entity.Advertisement;
import com.service.AdvertisementService;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    final AdvertisementDao ADS_DAO;

    final MailAddressService E_SERVICE;

    @Scheduled(cron = "0/5 * * * * *")
    public void deleteAllNotActive() {
        System.out.println(LocalTime.now());
    }

    @Override
    public void saveAndSentNotifications(Advertisement advertisement) {
        ADS_DAO.save(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return ADS_DAO.findById(id);
    }

    @Override
    public void updateAndSentNotifications(Advertisement advertisement) {
        ADS_DAO.update(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public void deleteById(int id) {
        ADS_DAO.deleteById(id);
    }

    @Override
    public int countAll() {
        return ADS_DAO.countAll();
    }


    @Override
    public Page<Advertisement> getByCategoryDto(CategoryDto categoryDto) {
        return ADS_DAO.getByCategoryDto(categoryDto);
    }

    @Override
    public Page<Advertisement> getByCategoryHeaderDto(CategoryHeaderDto categoryHeaderDto) {
        return ADS_DAO.getByCategoryHeaderDto(categoryHeaderDto);
    }

    @Override
    public Page<Advertisement> getByCategoryHeaderPriceDto(CategoryHeaderPriceDto chpDto) {
        return ADS_DAO.getByCategoryHeaderPriceDto(chpDto);
    }

}
