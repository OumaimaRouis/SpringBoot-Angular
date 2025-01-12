package com.gestion3;

import com.gestion3.entities.AppRole;
import com.gestion3.entities.AppUser;
import com.gestion3.entities.Book;
import com.gestion3.entities.Publisher;
import com.gestion3.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor

public class Gestion3Application {
    private PasswordEncoder passwordEncoder;


    //@Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BookRepository pr, AuthorRepository cr, BookDetailsRepository dr, PublisherRepository publisherRepository) {
        return args -> {
            Publisher p1 = Publisher.builder().nom("publisher2").build();
            publisherRepository.save(p1);
            Publisher p2 = Publisher.builder().nom("publisher3").build();
            publisherRepository.save(p2);
            Publisher p3 = Publisher.builder().nom("publisher3").build();
            publisherRepository.save(p3);
            pr.save(Book.builder().publishers(List.of(p1)).build());
            pr.save(Book.builder().publishers(List.of(p2)).build());
            pr.save(Book.builder().publishers(List.of(p3)).build());
            /*AppRole r1 = AppRole.builder().nom("USER").build();
            AppRole r2 = AppRole.builder().nom("ADMIN").build();
            appRoleRepository.save(r1);
            appRoleRepository.save(r2);
            AppUser u1= AppUser.builder().id(UUID.randomUUID().toString()).username("mohsen@gmail.com").password(passwordEncoder.encode("123")).roles(List.of(r1)).build();
            AppUser u2= AppUser.builder().id(UUID.randomUUID().toString()).username("salah@gmail.com").password(passwordEncoder.encode("123")).roles(List.of(r1,r2)).build();
            appUserRepository.save(u1);
            appUserRepository.save(u2);*/
            /*Publisher p1 = Publisher.builder().nom("publisher").build();
            publisherRepository.save(p1);
            pr.save(Book.builder().publishers(List.of(p1)).build());*/
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
