package com.test.integration_test.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Column(name = "book_name")
    private String book;

    @Column(name = "author")
    private String author;

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
