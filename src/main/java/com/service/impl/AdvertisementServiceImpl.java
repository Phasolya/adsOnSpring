package com.service.impl;

import com.dao.AdvertisementDao;
import com.domain.Advertisement;
import com.domain.User;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import com.service.AdvertisementService;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * {@link AdvertisementServiceImpl} class binds realization part with user
 * and binds {@link AdvertisementDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    final AdvertisementDao ADS_DAO;

    final MailAddressService E_SERVICE;

    @Scheduled(cron = "0/5 * * * * *")
    public void deleteAllNotActive() {

        ADS_DAO.deleteAllNotActive();

        System.out.println(LocalTime.now());
    }

    @Override
    public void deleteAllByUser(User user) {
        ADS_DAO.deleteAllByUser(user);
    }

    @Override
    public void saveAndSentNotifications(Advertisement advertisement) {
        save(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public void save(Advertisement entity) {
        ADS_DAO.save(entity);
    }

    @Override
    public Advertisement find(int id) {
        return ADS_DAO.find(id);
    }

    @Override
    public void update(Advertisement entity) {
        ADS_DAO.update(entity);
    }

    @Override
    public void updateAndSentNotifications(Advertisement advertisement) {
        update(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public void delete(int id) {
        ADS_DAO.delete(id);
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
