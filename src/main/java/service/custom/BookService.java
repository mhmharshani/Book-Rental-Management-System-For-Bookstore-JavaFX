package service.custom;

import model.dto.Book;
import model.dto.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BookService extends SuperService {

    boolean addBook(Book book) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    boolean deleteBook(String id) throws SQLException;

    Book searchBookById(String id) throws SQLException;

    List<Book> getAll() throws SQLException;

    List<String> getAllBookIDs() throws SQLException;
}
