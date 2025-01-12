package com.gestion3.controller;
import com.gestion3.entities.Author;
import com.gestion3.entities.BookDetails;
import com.gestion3.service.ServiceAuthor;
import com.gestion3.service.ServiceBookDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BookDetailsController {
    ServiceBookDetails bookDetails;
    @GetMapping("/api/details")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<BookDetails> getAllDetails(){
        return bookDetails.getAllDetails();
    }

}
