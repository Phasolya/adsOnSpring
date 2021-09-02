package com.service.impl;

import com.dao.MailAddressDAO;
import com.entity.Advertisement;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MailAddressServiceImpl implements MailAddressService {

    final MailAddressDAO MAIL_ADDRESS_DAO;

    public MailAddressServiceImpl(@Autowired MailAddressDAO mailAddressDAO) {
        MAIL_ADDRESS_DAO = mailAddressDAO;
    }

    private List<String> findAllSuitableEmails(Advertisement ads) {
        return MAIL_ADDRESS_DAO.findAllSuitableEmails(ads);
    }

    @Override
    public void sendEmails(Advertisement advertisement) {
        List<String> emails = findAllSuitableEmails(advertisement);
        //sending
        System.out.println("here we will sent mails to our costumers");
    }
}
