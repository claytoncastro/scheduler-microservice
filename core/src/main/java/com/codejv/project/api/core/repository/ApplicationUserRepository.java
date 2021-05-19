package com.codejv.project.api.core.repository;

import com.codejv.project.api.core.model.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    @Query(value = "SELECT a.* FROM tb_application_user a WHERE a.username=:username", nativeQuery = true)
    ApplicationUser findByUsername(@Param("username")String username);
}
