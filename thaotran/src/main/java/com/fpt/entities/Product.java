package com.fpt.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "name_product", length = 20)
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "category_code", referencedColumnName = "code")
    private Category category;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "producer", referencedColumnName = "id_provider")
    private Provider producer;

    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id_provider")
    private Provider supplier;

    private Integer createBy;

    private Integer updateBy;


    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;


}
