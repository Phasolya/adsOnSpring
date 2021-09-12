package com.dao.impl;

import com.dao.MailAddressDao;
import com.repository.MailAddressRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailAddressDaoImpl implements MailAddressDao {

    final MailAddressRepository MAIL_AD_REPOSITORY;

    @Override
    public List<String> findAllSuitableEmails(int categoryId, BigDecimal asdPrice, String title) {
        return MAIL_AD_REPOSITORY.findAllSuitableEmails(categoryId, asdPrice, title);
    }
}
