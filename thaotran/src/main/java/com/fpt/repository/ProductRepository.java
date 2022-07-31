package com.fpt.repository;

import com.fpt.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select p from Product p " +
            "join Category c " +
            "on p.category = c.code " +
            "join Provider pr " +
            "on p.producer = pr.idProvider " +
            " where p.nameProduct like %?1% and c.categoryName like %?2% and pr.nameProvider like %?3%",

            countQuery = "select count(p) from Product p join Category c on p.category = c.code" +
                    " where p.nameProduct like %?1% and c.categoryName like %?2%  "
    )
    Page<Product> findByNameProductLike(String nameProduct, String category, String provider, Pageable pageable);
}
