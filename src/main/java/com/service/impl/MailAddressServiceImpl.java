package com.service.impl;

import com.dao.MailAddressDAO;
import com.entity.Advertisement;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MailAddressServiceImpl implements MailAddressService {

    final MailAddressDAO MAIL_ADDRESS_DAO;

    @Override
    public void sendEmails(Advertisement advertisement) {

        int categoryId = advertisement.getCategory().getId();
        BigDecimal adsPrice = advertisement.getPrice();
        String title = advertisement.getHeader();

        List<String> emails = MAIL_ADDRESS_DAO.findAllSuitableEmails(categoryId, adsPrice, title);
        System.out.println(emails.size());
        emails.forEach(System.out::println);
        //sending
        System.out.println("here we will sent mails to our costumers");
    }
}
