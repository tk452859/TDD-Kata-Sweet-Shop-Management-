package org.example.sweet_shop.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    private String role = "CUSTOMER"; // CUSTOMER or ADMIN

    // Default constructor
    public User() {}

    // Constructor for easy creation
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
