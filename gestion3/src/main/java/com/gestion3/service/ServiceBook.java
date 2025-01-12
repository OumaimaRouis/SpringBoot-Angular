package com.gestion3.service;

import com.gestion3.dto.BookDTO;
import com.gestion3.entities.Book;
import com.gestion3.mappers.BookMapper;
import com.gestion3.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceBook implements IServiceBook {

    //@Autowired
    private BookRepository bookRepository;
    private BookMapper bookMapper;


    @Override
    public void addBook(Book p, MultipartFile mf) throws IOException {
        if(!mf.isEmpty())
            p.setImg(uploadImage(mf));
        bookRepository.save(p);
    }

    @Override
    public List<BookDTO> gtAllBooks() {
        List<Book>l=bookRepository.findAll();
        List<BookDTO>booksdto=new ArrayList<>();
        for (Book p:l)
            booksdto.add(bookMapper.bookDTOFrom(p));
        return booksdto;
    }
    @Override
    public Page<Book> getBooksByMC(String mc, Pageable p) {
        return bookRepository.rechercherParMc(mc, p);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(Book p) {
        Optional<Book> existingBook = bookRepository.findById(p.getId());

        if (existingBook.isPresent()) {
            // If the book exists, update the fields as needed and save it
            Book updatedBook = existingBook.get();
            updatedBook.setTitle(p.getTitle());
            updatedBook.setAuthor(p.getAuthor());
            updatedBook.setBookDetails(p.getBookDetails());
            updatedBook.setImg(p.getImg());

            bookRepository.save(updatedBook); // Save the updated book
        } else {
            throw new EntityNotFoundException("Book with ID " + p.getId() + " not found");
        }    }

    @Override
    public byte[] getImage(Long id) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Photos",getBook(id).getImg()));
    }




    private String uploadImage(MultipartFile mf) throws IOException {
        String oldName=mf.getOriginalFilename();
        String newName= giveMeNewName(oldName);

        Path p= Paths.get(System.getProperty("user.home")+"/Photos",newName);
        Files.write(p, mf.getBytes());
        return newName;

    }

    private String giveMeNewName(String oldName){
        String firstpart= oldName.substring(0, oldName.lastIndexOf("."));
        String secondpart= oldName.substring(oldName.lastIndexOf(".")+1);
        return firstpart+System.currentTimeMillis()+"."+secondpart;
    }

    public byte[] imageToByteArray(String pathImage)throws IOException{
        Path path = Path.of(pathImage);
        return Files.readAllBytes(path);
    }
}