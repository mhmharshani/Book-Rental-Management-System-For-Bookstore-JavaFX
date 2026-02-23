package repository.custom.impl;

import model.dto.Book;
import repository.custom.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public boolean create(Book book) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Book book) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(String s) throws SQLException {
        return false;
    }

    @Override
    public Book getById(String s) throws SQLException {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return List.of();
    }
}
