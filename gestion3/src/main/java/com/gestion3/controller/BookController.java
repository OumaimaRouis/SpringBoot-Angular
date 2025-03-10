package com.gestion3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion3.dto.BookDTO;
import com.gestion3.entities.Book;
import com.gestion3.repository.BookRepository;
import com.gestion3.service.IServiceBook;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class BookController {
    private IServiceBook sp;
    private BookRepository pr;

    @GetMapping("/api/books")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<BookDTO> getProduits(){
        return sp.gtAllBooks();
    }

    @GetMapping("/api/book/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Book getProducts(@PathVariable Long id){
        return sp.getBook(id);
    }

   /* @GetMapping("/products/{mc}")
    public List<Produit> getProductsByMC(@PathVariable String mc) {
        return pr.findByNameContains(mc);
    }*/

    @DeleteMapping("/api/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id)
    {
        sp.deleteBook(id);
        return "ok";
    }

    @PostMapping("/api/save")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void addBook(@RequestBody Book p)
    {
        pr.save(p);
    }


    @PostMapping(path= "/api/saveWithImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void addBook2(@RequestParam(required = false) MultipartFile mf, @RequestParam String produit) throws IOException
    {
        Book p =new ObjectMapper().readValue(produit, Book.class);
        sp.addBook(p,mf);

        System.out.println("Produit : " + produit);
        System.out.println("File : " + (mf != null ? mf.getOriginalFilename() : "No file uploaded"));
    }

    @PutMapping("/api/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        // Update the book
        sp.updateBook(book);
    }

    @GetMapping(value = "/api/getImage/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            byte[] image = sp.getImage(id);  // Assuming sp.getImage(id) returns the image bytes
            if (image == null) {
                return ResponseEntity.notFound().build();  // Return 404 if image is not found
            }

            // You can set the correct content type based on the image type, e.g., JPEG, PNG
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)  // Or dynamically detect image type
                    .body(image);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // Return 500 if an error occurs
        }
    }

}
