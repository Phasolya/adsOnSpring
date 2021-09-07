package com.dao.impl;

import com.dao.MailAddressDAO;
import com.entity.Advertisement;
import com.entity.Category;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
@Transactional
public class MailAddressDAOImpl /*implements MailAddressDAO*/ {

//    @PersistenceContext
//    EntityManager manager;
//
//    @Override
//    public List<String> findAllSuitableEmails(Advertisement ads) {
//
//        Query query = manager.createQuery("SELECT ma.user.email.mailAddress " +
//                "FROM MatchingAd ma " +
//                "WHERE ma.category.id = :categoryId " +
//                "AND ma.priceFrom <= :asdPrice " +
//                "AND ma.priceTo >= :asdPrice " +
//                "AND ma.title LIKE CONCAT('%', :title, '%')", String.class);
//
//        query.setParameter("categoryId", ads.getCategory().getId());
//
//        query.setParameter("asdPrice", ads.getPrice());
//
//        query.setParameter("title", ads.getHeader());
//
//        List<String> resultList = query.getResultList();
//
//        return resultList;
//    }
}
