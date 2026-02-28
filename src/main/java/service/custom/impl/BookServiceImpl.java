package service.custom.impl;

import model.dto.Author;
import model.dto.Book;
import model.dto.Customer;
import repository.RepositoryFactory;
import repository.custom.AuthorRepository;
import repository.custom.BookRepository;
import service.custom.BookService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepository bookRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);

    @Override
    public boolean addBook(Book book) throws SQLException {
        return bookRepositoryType.create(book);
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        return bookRepositoryType.update(book);
    }

    @Override
    public boolean deleteBook(String id) throws SQLException {
        return bookRepositoryType.deleteById(id);
    }

    @Override
    public Book searchBookById(String id) throws SQLException {
        return bookRepositoryType.getById(id);
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return bookRepositoryType.getAll();
    }

    @Override
    public List<String> getAllBookIDs() throws SQLException {
        List<Book> all = getAll();
        ArrayList<String> idList = new ArrayList<>();

        for(Book book: all){
            idList.add(book.getId());
        }
        return idList;
    }
}
