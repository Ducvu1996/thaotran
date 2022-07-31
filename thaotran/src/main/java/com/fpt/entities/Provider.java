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
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Integer idProvider;

    private String nameProvider;

    @Pattern(regexp = "^A|S|P")
    @Column(name = "type_provider", length = 1)
    private String typeProvider;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private List<Product> listProductProducer;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> listProductSupplier;


    private Integer createBy;

    private Integer updateBy;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;


}
