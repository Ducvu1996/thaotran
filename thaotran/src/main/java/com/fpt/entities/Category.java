package com.fpt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
public class Category {
    @Id
    @Pattern(regexp = "^CAT+[0-9]{3}")
    @Column(name = "code", length = 6)
    private String code;
    @Column(name = "category_name", length = 30)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> listProduct;


    private Integer createBy;

    private Integer updateBy;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

}
