package com.repository;


import com.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link UserRepository} interface serves to simplify the data access
 * process for {@link User} in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository}.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
    long countByLogin(String login);

}
