package com.fpt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;
    @Column(name = "full_name", length = 40, nullable = false)
    private String fullName;
    @Email
    private String email;
    private String role;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "Created_at", columnDefinition = "datetime", nullable = false)
    private Instant createdAt;

    @Column(name = "Update_at", columnDefinition = "datetime", nullable = false)
    private Instant updateAt;


}
