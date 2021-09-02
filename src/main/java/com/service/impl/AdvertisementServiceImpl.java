package com.service.impl;

import com.dao.AdvertisementDAO;
import com.entity.Advertisement;
import com.entity.Category;
import com.service.AdvertisementService;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdvertisementServiceImpl implements AdvertisementService {

    final AdvertisementDAO ADS_DAO;

    final MailAddressService eService;

    public AdvertisementServiceImpl(AdvertisementDAO adsDao, MailAddressService eService) {
        ADS_DAO = adsDao;
        this.eService = eService;
    }

    @Override
    public void save(Advertisement advertisement) {
        ADS_DAO.save(advertisement);
        eService.sendEmails(advertisement);
    }

    @Override
    public Advertisement findById(int id) {
        return ADS_DAO.findById(id);
    }

    @Override
    public void update(Advertisement advertisement) {
        ADS_DAO.update(advertisement);
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
    public List<Advertisement> selectAll() {
        return ADS_DAO.selectAll();
    }

    @Override
    public int countBy(Category category) {
        return ADS_DAO.countBy(category);
    }

    @Override
    public List<Advertisement> selectByPriceUp(Category category, int startRow, int amount) {
        return ADS_DAO.selectByPriceUp(category, startRow, amount);
    }

    @Override
    public List<Advertisement> selectByPriceDown(Category category, int startRow, int amount) {
        return ADS_DAO.selectByPriceDown(category, startRow, amount);
    }

    @Override
    public int countBy(Category category, String title) {
        return ADS_DAO.countBy(category, title);
    }

    @Override
    public List<Advertisement> selectByPriceUp(Category category, String title, int startRow, int amount) {
        return ADS_DAO.selectByPriceUp(category, title, startRow, amount);
    }

    @Override
    public List<Advertisement> selectByPriceDown(Category category, String title, int startRow, int amount) {
        return ADS_DAO.selectByPriceDown(category, title, startRow, amount);
    }

    @Override
    public int countBy(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo) {
        return ADS_DAO.countBy(category, title, priceFrom, priceTo);
    }

    @Override
    public List<Advertisement> selectByPriceUp(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, int startRow, int amount) {
        return ADS_DAO.selectByPriceUp(category, title, priceFrom, priceTo, startRow, amount);
    }

    @Override
    public List<Advertisement> selectByPriceDown(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo, int startRow, int amount) {
        return ADS_DAO.selectByPriceDown(category, title, priceFrom, priceTo, startRow, amount);
    }

}
