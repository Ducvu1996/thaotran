package com.fpt.repository;

import com.fpt.entities.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    @Query(value = "select pr from Provider pr where pr.nameProvider like %?1%",
            countQuery = "select count(pr) from Provider pr where pr.nameProvider like %?1%"
    )
    Page<Provider> findByNameProviderLike(String nameProvider, Pageable pageable);
}
