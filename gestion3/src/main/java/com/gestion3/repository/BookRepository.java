package com.gestion3.repository;

import com.gestion3.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book,Long> {
    public Page<Book> findByTitleContains(String mc, Pageable p);
    @Query("select p from Book  p where p.title like %:x% ")
    public Page<Book> rechercherParMc(@Param("x") String mc, Pageable p);


}
