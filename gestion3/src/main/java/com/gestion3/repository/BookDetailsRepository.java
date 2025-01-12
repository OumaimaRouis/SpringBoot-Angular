package com.gestion3.repository;

import com.gestion3.entities.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
}
