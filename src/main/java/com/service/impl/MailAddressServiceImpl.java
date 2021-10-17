package com.service.impl;

import com.dao.MailAddressDao;
import com.domain.Advertisement;
import com.service.MailAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@link MailAddressServiceImpl} class binds realization part with user
 * and binds {@link MailAddressDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class MailAddressServiceImpl implements MailAddressService {

    final MailAddressDao MAIL_ADDRESS_DAO;

    final JavaMailSender sender;

    @Override
    public void sendEmails(Advertisement advertisement) {

        List<String> emails = findSuitableEmails(advertisement);

        for (String email : emails) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("Greetings, new Advertisement you was waited for!");
            message.setText("Header: "+ advertisement.getHeader());
            message.setText("Price: "+ advertisement.getPrice());

            sender.send(message);
        }

    }

    private List<String> findSuitableEmails(Advertisement advertisement) {
        int categoryId = advertisement.getCategory().getId();
        BigDecimal adsPrice = advertisement.getPrice();
        String title = advertisement.getHeader();

        return MAIL_ADDRESS_DAO.findAllSuitableEmails(categoryId, adsPrice, title);
    }
}
