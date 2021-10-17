package com.dao;

import com.domain.Advertisement;
import com.domain.User;
import com.dto.CategoryDto;
import com.dto.CategoryHeaderDto;
import com.dto.CategoryHeaderPriceDto;
import org.springframework.data.domain.Page;

/**
 * {@link AdvertisementDao} interface serves the data access process for {@link Advertisement}
 * in database for crud operations and other operations.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface AdvertisementDao extends CrudDao<Advertisement> {

    int countAll();

    public void deleteAllNotActive();

    public void deleteAllByUser(User user);

    Page<Advertisement> getByCategoryDto(CategoryDto categoryDto);

    Page<Advertisement> getByCategoryHeaderDto(CategoryHeaderDto categoryHeaderDto);

    Page<Advertisement> getByCategoryHeaderPriceDto(CategoryHeaderPriceDto chpDto);

}
