package com.test.integration_test.repository;

import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BookResponseDto findById(int id) {
        String sql = "SELECT * FROM BUSINESS_DATA.BOOK WHERE book_id = :bookId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("bookId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(BookResponseDto.class));
    }

    public void update(Book book){
        String sql = "INSERT INTO BUSINESS_DATA.BOOK (book_name, author) VALUES(:name, :author)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", book.getBook())
                .addValue("author", book.getAuthor());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder, new String[]{"book_id"});
        Number generatedId = keyHolder.getKey();
        book.setId(generatedId.intValue());
    }

    public List<BookResponseDto> findAll(){
        String sql = "SELECT * FROM BUSINESS_DATA.BOOK";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookResponseDto.class));
    }
}
