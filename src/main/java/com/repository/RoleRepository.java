package com.repository;


import com.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link RoleRepository} interface serves to simplify the data access
 * process for {@link Role} in database,binds database to implementation
 * part,has extends CRUD methods from {@link JpaRepository}.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String roleName);
}
