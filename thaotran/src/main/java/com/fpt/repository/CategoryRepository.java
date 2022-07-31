package com.fpt.repository;

import com.fpt.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "select c from Category c where c.categoryName like %?1%",
            countQuery = "select count(c) from Category c where c.categoryName like %?1%"
    )
    Page<Category> findByCategoryNameLike(String nameCategory, Pageable pageable);
}
