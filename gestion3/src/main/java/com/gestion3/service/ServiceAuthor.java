package com.gestion3.service;

import com.gestion3.entities.Author;
import com.gestion3.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServiceAuthor implements IServiceAuthor {
    private AuthorRepository authorRepository;

    @Override
    public void addAuthor(Author c) {
        authorRepository.save(c);
    }

    @Override
    public List<Author> gtAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);

    }

    @Override
    public void updateAuthor(Author c) {
        authorRepository.save(c);

    }
}