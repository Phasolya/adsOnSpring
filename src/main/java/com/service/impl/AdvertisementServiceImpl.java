package com.service.impl;

import com.dao.AdvertisementDAO;
import com.entity.Advertisement;
import com.entity.Category;
import com.service.AdvertisementService;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdvertisementServiceImpl implements AdvertisementService {

    final AdvertisementDAO ADS_DAO;

    final MailAddressService E_SERVICE;

    @Override
    public void saveAndSentNotifications(Advertisement advertisement) {
        ADS_DAO.save(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return ADS_DAO.findById(id).get();
    }

    @Override
    public void updateAndSentNotifications(Advertisement advertisement) {
        ADS_DAO.save(advertisement);
        E_SERVICE.sendEmails(advertisement);
    }

    @Override
    public void deleteById(int id) {
        ADS_DAO.deleteById(id);
    }

    @Override
    public int countAll() {
        return Math.toIntExact(ADS_DAO.count());
    }

    @Override
    public int countByCategory(Category category) {
        return ADS_DAO.countByCategory(category);
    }

    @Override
    public List<Advertisement> getByCategoryOrderByPrice(Category category, int startRow, int amount) {

        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryOrderByPrice(category, pageable);
    }

    @Override
    public List<Advertisement> getByCategoryOrderByPriceDesc(Category category, int startRow, int amount) {

        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryOrderByPriceDesc(category, pageable);
    }

    @Override
    public int countByCategoryAndHeader(Category category, String title) {
        return ADS_DAO.countByCategoryAndHeader(category, title);
    }

    @Override
    public List<Advertisement> getByCategoryAndHeaderOrderByPrice(Category category, String title,
                                                                  int startRow, int amount) {
        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryAndHeaderOrderByPrice(category, title, pageable);
    }

    @Override
    public List<Advertisement> getByCategoryAndHeaderOrderByPriceDesc(Category category, String title,
                                                                      int startRow, int amount) {
        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryAndHeaderOrderByPriceDesc(category, title, pageable);
    }

    @Override
    public int countByCategoryAndHeaderAndPriceBetween(Category category, String title,
                                                       BigDecimal priceFrom, BigDecimal priceTo) {
        return ADS_DAO.countByCategoryAndHeaderAndPriceBetween(category, title, priceFrom, priceTo);
    }

    @Override
    public List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPrice(Category category, String title,
                                                                                 BigDecimal priceFrom, BigDecimal priceTo,
                                                                                 int startRow, int amount) {
        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryAndHeaderAndPriceBetweenOrderByPrice(category, title, priceFrom, priceTo, pageable);
    }

    @Override
    public List<Advertisement> getByCategoryAndHeaderAndPriceBetweenOrderByPriceDesc(Category category, String title,
                                                                                     BigDecimal priceFrom, BigDecimal priceTo,
                                                                                     int startRow, int amount) {
        Pageable pageable = PageRequest.of(startRow, amount);

        return ADS_DAO.getByCategoryAndHeaderAndPriceBetweenOrderByPriceDesc(category, title, priceFrom, priceTo, pageable);
    }

}
