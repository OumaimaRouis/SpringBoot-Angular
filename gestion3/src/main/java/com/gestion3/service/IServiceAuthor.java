package com.gestion3.service;

import com.gestion3.entities.Author;

import java.util.List;

public interface IServiceAuthor {

    public void addAuthor(Author c);
    public List<Author> gtAllAuthors();
    public Author getAuthor(Long id);
    public void deleteAuthor(Long id);
    public void updateAuthor(Author c);
}
