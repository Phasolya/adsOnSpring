package com.dao;

import com.entity.Advertisement;

import java.util.List;

public interface MailAddressDAO {

    List<String> findAllSuitableEmails(Advertisement ads);

}
