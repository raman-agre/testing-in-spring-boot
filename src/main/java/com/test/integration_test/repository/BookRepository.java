package com.test.integration_test.repository;

import com.test.integration_test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BookRepository{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Book findById(int id) {
        String sql = "SELECT * FROM BUSINESS_DATA.BOOK WHERE book_id = :bookId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("bookId", id);
        return namedParameterJdbcTemplate
                .queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(Book.class));
    }

    public int  addBook(Book book){
        String sql = "INSERT INTO BUSINESS_DATA.BOOK (bookName, book_id, author) VALUES(:name, :id, :author)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", book.getBook())
                .addValue("id", book.getId())
                .addValue("author", book.getAuthor());
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
