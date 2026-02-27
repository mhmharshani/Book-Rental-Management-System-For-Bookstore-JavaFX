package service.custom;

import model.dto.Author;
import model.dto.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface AuthorService extends SuperService {

    boolean addAuthor(Author author) throws SQLException;

    boolean updateAuthor(Author author) throws SQLException;

    boolean deleteAuthor(String id) throws SQLException;

    Author searchAuthorById(String id) throws SQLException;

    List<Author> getAll() throws SQLException;

    List<String> getAllAuthorIDs() throws SQLException;
}
