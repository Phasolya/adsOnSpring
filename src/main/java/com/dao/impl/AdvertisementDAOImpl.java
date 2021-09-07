package com.dao.impl;

import com.dao.AdvertisementDAO;
import com.entity.Advertisement;
import com.entity.Category;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Repository
@Transactional
public class AdvertisementDAOImpl /*implements AdvertisementDAO*/ {

//    @PersistenceContext
//    EntityManager manager;
//
//    @Override
//    public void save(Advertisement advertisement) {
//
//        manager.persist(advertisement);
//
//    }
//
//    @Override
//    public Advertisement findById(int id) {
//
//        return manager.find(Advertisement.class, id);
//
//    }
//
//    @Override
//    public void update(Advertisement advertisement) {
//
//        Advertisement merged = manager.merge(advertisement);
//
//        manager.persist(merged);
//
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//        Advertisement reference = manager.getReference(Advertisement.class, id);
//
//        manager.remove(reference);
//
//    }
//
//    @Override
//    public int countAll() {
//
//        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//
//        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
//
//        cq.select(criteriaBuilder.count(cq.from(Category.class)));
//
//        return Math.toIntExact(manager.createQuery(cq).getSingleResult());
//
//    }
//
//    @Override
//    public List<Advertisement> selectAll() {
//
//        Query query = manager.createQuery("FROM Advertisement a ORDER BY a.publicationDate", Advertisement.class);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    // ===============================================================================================================
//
//    @Override
//    public int countBy(Category category) {
//
//        Query query = manager.createQuery("SELECT COUNT(a) FROM Advertisement a WHERE Advertisement.category.id = :categoryId", Integer.class);
//
//        query.setParameter("categoryId", category.getId());
//
//        Integer result = (Integer) query.getSingleResult();
//
//        return result;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceUp(Category category, int startRow, int amount) {
//
//        Query query = manager.createQuery("FROM Advertisement a WHERE a.category.id = :categoryID ORDER BY a.price", Advertisement.class);
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceDown(Category category, int startRow, int amount) {
//
//        Query query = manager.createQuery("FROM Advertisement a WHERE a.category.id = :categoryID ORDER BY a.price DESC", Advertisement.class);
//
//        query.setParameter("categoryID", category.getId());
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    // ===============================================================================================================
//
//    @Override
//    public int countBy(Category category, String title) {
//
//        Query query = manager.createQuery(
//                "SELECT COUNT(a) FROM Advertisement a " +
//                        "WHERE Advertisement.category.id = :categoryId AND Advertisement.header LIKE :title ",
//                Integer.class);
//
//        query.setParameter("categoryId", category.getId());
//
//        query.setParameter("title", title);
//
//        Integer result = (Integer) query.getSingleResult();
//
//        return result;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceUp(Category category, String title, int startRow, int amount) {
//
//        Query query = manager.createQuery(
//                "FROM Advertisement a " +
//                        "WHERE a.category.id = :categoryID AND Advertisement.header LIKE :title " +
//                        "ORDER BY a.price",
//                Advertisement.class);
//
//        query.setParameter("categoryID", category.getId());
//
//        query.setParameter("title", title);
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceDown(Category category, String title, int startRow, int amount) {
//
//        Query query = manager.createQuery(
//                "FROM Advertisement a " +
//                        "WHERE a.category.id = :categoryID AND Advertisement.header LIKE :title " +
//                        "ORDER BY a.price DESC",
//                Advertisement.class);
//
//        query.setParameter("categoryID", category.getId());
//
//        query.setParameter("title", title);
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    // ===============================================================================================================
//
//    @Override
//    public int countBy(Category category, String title, BigDecimal priceFrom, BigDecimal priceTo) {
//
//        Query query = manager.createQuery(
//                "SELECT COUNT(a) FROM Advertisement a " +
//                        "WHERE Advertisement.category.id = :categoryId " +
//                        "AND Advertisement.header LIKE :title " +
//                        "AND a.price <= :priceTo AND a.price >= :priceFrom",
//                Integer.class);
//
//        query.setParameter("categoryId", category.getId());
//
//        query.setParameter("title", title);
//
//        query.setParameter("priceTo", priceTo);
//
//        query.setParameter("priceFrom", priceFrom);
//
//        Integer result = (Integer) query.getSingleResult();
//
//        return result;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceUp(Category category, String title, BigDecimal priceFrom,
//                                               BigDecimal priceTo, int startRow, int amount) {
//
//        Query query = manager.createQuery(
//                "FROM Advertisement a " +
//                        "WHERE a.category.id = :categoryID AND Advertisement.header LIKE :title " +
//                        "AND a.price <= :priceTo AND a.price >= :priceFrom " +
//                        "ORDER BY a.price",
//                Advertisement.class);
//
//        query.setParameter("categoryID", category.getId());
//
//        query.setParameter("title", title);
//
//        query.setParameter("priceTo", priceTo);
//
//        query.setParameter("priceFrom", priceFrom);
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }
//
//    @Override
//    public List<Advertisement> selectByPriceDown(Category category, String title, BigDecimal priceFrom,
//                                                 BigDecimal priceTo, int startRow, int amount) {
//
//        Query query = manager.createQuery(
//                "FROM Advertisement a " +
//                        "WHERE a.category.id = :categoryID AND Advertisement.header LIKE :title " +
//                        "AND a.price <= :priceTo AND a.price >= :priceFrom " +
//                        "ORDER BY a.price DESC ",
//                Advertisement.class);
//
//        query.setParameter("categoryID", category.getId());
//
//        query.setParameter("title", title);
//
//        query.setParameter("priceTo", priceTo);
//
//        query.setParameter("priceFrom", priceFrom);
//
//        query.setFirstResult(startRow);
//
//        query.setMaxResults(amount);
//
//        List<Advertisement> resultList = query.getResultList();
//
//        return resultList;
//    }

}
