package com.gestion3.controller;
import com.gestion3.entities.Author;
import com.gestion3.entities.BookDetails;
import com.gestion3.service.ServiceAuthor;
import com.gestion3.service.ServiceBookDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class BookDetailsController {
    ServiceBookDetails bookDetails;
    @GetMapping("/details")
    public List<BookDetails> getAllDetails(){
        return bookDetails.getAllDetails();
    }

}
