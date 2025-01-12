package com.gestion3.dto;

import com.gestion3.entities.Author;
import com.gestion3.entities.BookDetails;
import com.gestion3.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class BookDTO {

    private Long id;
    private String title;
    private String img;
    private Author author;
    private BookDetails bookDetails;
    private Publisher publisher;
}
