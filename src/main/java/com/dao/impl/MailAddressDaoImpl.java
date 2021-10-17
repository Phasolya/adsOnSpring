package com.dao.impl;

import com.dao.MailAddressDao;
import com.repository.MailAddressRepository;
import com.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@link MailAddressDaoImpl} class serves for the data access process for
 * {@link String} in database.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailAddressDaoImpl implements MailAddressDao {

    /**
     * This is object instance of {@link UserRepository}
     * helps us persist data into data base with Spring Data Jpa.
     */
    final MailAddressRepository MAIL_AD_REPOSITORY;

    @Override
    public List<String> findAllSuitableEmails(int categoryId, BigDecimal asdPrice, String title) {
        return MAIL_AD_REPOSITORY.findAllSuitableEmails(categoryId, asdPrice, title);
    }
}
