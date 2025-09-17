package org.maksymtiutiunnyk.somcooked.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.maksymtiutiunnyk.somcooked.enums.Role;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role =  Role.USER;

    @Column(nullable = false)
    private boolean enable = true;
}
