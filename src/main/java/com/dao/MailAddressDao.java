package com.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@link MailAddressDao} interface serves the data access process for {@link String}.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface MailAddressDao {

    List<String> findAllSuitableEmails(int categoryId, BigDecimal asdPrice, String title);

}
