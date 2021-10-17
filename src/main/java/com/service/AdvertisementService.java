package com.service;

import com.domain.Advertisement;
import com.domain.User;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import org.springframework.data.domain.Page;

/**
 * {@link AdvertisementService} interface binds realization part
 * with user and binds {@link com.dao.AdvertisementDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface AdvertisementService extends CrudService<Advertisement> {

    int countAll();

    public void deleteAllNotActive();

    public void deleteAllByUser(User user);

    public void saveAndSentNotifications(Advertisement advertisement);

    public void updateAndSentNotifications(Advertisement advertisement);

    Page<Advertisement> getByCategoryDto(CategoryDto categoryDto);

    Page<Advertisement> getByCategoryHeaderDto(CategoryHeaderDto categoryHeaderDto);

    Page<Advertisement> getByCategoryHeaderPriceDto(CategoryHeaderPriceDto chpDto);

}
