package com.gestion3.service;

import com.gestion3.dto.BookDTO;
import com.gestion3.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceBook {
    public void addBook(Book p, MultipartFile mf) throws IOException;
    public List<BookDTO> gtAllBooks();
    public Page<Book> getBooksByMC(String mc, Pageable p);
    public Book getBook(Long id);
    public void deleteBook(Long id);
    public void updateBook(Book p);
    public byte[] getImage(Long id) throws IOException;

}