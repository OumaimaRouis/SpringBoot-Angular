package com.gestion3.mappers;

import com.gestion3.dto.BookDTO;
import com.gestion3.entities.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public BookDTO bookDTOFrom(Book p)
    {
        BookDTO pdto = new BookDTO();
        BeanUtils.copyProperties(p,pdto); //tnajem tekhdem b setters et getters
        return pdto;
    }
}
