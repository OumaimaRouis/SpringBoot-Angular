package com.gestion3.service;

import com.gestion3.entities.BookDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IServiceBookDetails {
    public void addDetails(BookDetails d);
    public List<BookDetails> getAllDetails();
    public BookDetails getDetails(Long id);
    public void deleteDetails(Long id);
    public void updateDetails(BookDetails d);
}
