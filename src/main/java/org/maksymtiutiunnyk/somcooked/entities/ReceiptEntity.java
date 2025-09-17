package org.maksymtiutiunnyk.somcooked.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "receipts")
@Data
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String steps;

    @Column(nullable = false)
    private boolean isPublic;

    @Column(nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_receipt_user"))
    private UserEntity user;
}
