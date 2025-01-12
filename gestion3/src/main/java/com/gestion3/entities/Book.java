package com.gestion3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
    private String img;
    @OneToOne(fetch = FetchType.EAGER)
    private BookDetails bookDetails;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Publisher> publishers;



}
