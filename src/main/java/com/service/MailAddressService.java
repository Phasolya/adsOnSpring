package com.service;

import com.domain.Advertisement;

/**
 * {@link MailAddressService} interface binds realization part
 * with user and binds {@link com.dao.MailAddressDao} layer.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MailAddressService {

    void sendEmails(Advertisement advertisement);
}
