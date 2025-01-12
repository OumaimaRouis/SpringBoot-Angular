package com.gestion3.controller;
import com.gestion3.entities.Author;
import com.gestion3.service.ServiceAuthor;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthorController {
    private ServiceAuthor serviceAuthor;

    @GetMapping("/api/authors")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Author> gtAllAuthors(){
        return serviceAuthor.gtAllAuthors();
    }
}
