package com.dao;

import java.math.BigDecimal;
import java.util.List;

public interface MailAddressDao {

    List<String>findAllSuitableEmails(int categoryId, BigDecimal asdPrice, String title);

}
