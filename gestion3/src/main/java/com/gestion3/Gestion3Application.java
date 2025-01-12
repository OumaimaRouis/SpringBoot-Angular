package com.gestion3;

import com.gestion3.entities.Author;
import com.gestion3.entities.Book;
import com.gestion3.entities.BookDetails;
import com.gestion3.repository.AuthorRepository;
import com.gestion3.repository.BookDetailsRepository;
import com.gestion3.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Gestion3Application {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository pr, AuthorRepository cr, BookDetailsRepository dr) {
        return args -> {
        /*Author a1 = new Author(null,"author1",null);
        cr.save(a1);
        Author a2 = new Author(null,"author2",null);
        cr.save(a2);
        pr.save(Book.builder().title("titre").author(a1).build());
            pr.save(Book.builder().title("titre2").author(a2).build());

            BookDetails bookDetails = new BookDetails(null,"summary","arabe",null);
            dr.save(bookDetails);
            pr.save(Book.builder().bookDetails(bookDetails).build());

        //cr.save(new Categorie(null, "informatique",null));

      // cr.save(Categorie.builder().nom("electronique").build());

            List<Book> list = pr.findAll();
            list.forEach(p->System.out.println(p.getTitle()));

            //  System.out.println(pr.findAll());*/
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Gestion3Application.class, args);
    }

}
