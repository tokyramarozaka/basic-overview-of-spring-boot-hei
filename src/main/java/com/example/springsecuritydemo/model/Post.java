package com.example.springsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "\"post\"")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant postingDate;

    @NotBlank(message = "Post content must not be blank.")
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User user;
}
