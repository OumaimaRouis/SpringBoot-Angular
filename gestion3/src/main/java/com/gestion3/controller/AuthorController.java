package com.gestion3.controller;
import com.gestion3.entities.Author;
import com.gestion3.service.ServiceAuthor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthorController {
    private ServiceAuthor serviceAuthor;

    @GetMapping("/authors")
    public List<Author> gtAllAuthors(){
        return serviceAuthor.gtAllAuthors();
    }
}
