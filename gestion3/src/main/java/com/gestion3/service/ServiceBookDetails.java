package com.gestion3.service;

import com.gestion3.entities.BookDetails;
import com.gestion3.repository.BookDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServiceBookDetails implements IServiceBookDetails{
    private BookDetailsRepository bookDetailsRepository;
    @Override
    public void addDetails(BookDetails d) {
        bookDetailsRepository.save(d);

    }

    @Override
    public List<BookDetails> getAllDetails() {
        return bookDetailsRepository.findAll();
    }

    @Override
    public BookDetails getDetails(Long id) {
        return bookDetailsRepository.findById(id).get();
    }

    @Override
    public void deleteDetails(Long id) {
        bookDetailsRepository.deleteById(id);

    }

    @Override
    public void updateDetails(BookDetails d) {
        bookDetailsRepository.save(d);

    }
}
