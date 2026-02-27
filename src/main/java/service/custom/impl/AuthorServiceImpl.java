package service.custom.impl;

import model.dto.Author;
import repository.RepositoryFactory;
import repository.custom.AuthorRepository;
import repository.custom.CustomerRepository;
import service.custom.AuthorService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.AUTHOR);

    @Override
    public boolean addAuthor(Author author) throws SQLException {
        return false;
    }

    @Override
    public boolean updateAuthor(Author author) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAuthor(String id) throws SQLException {
        return false;
    }

    @Override
    public Author searchAuthorById(String id) throws SQLException {
        return authorRepositoryType.getById(id);
    }

    @Override
    public List<Author> getAll() throws SQLException {
        return authorRepositoryType.getAll();
    }

    @Override
    public List<String> getAllAuthorIDs() throws SQLException {
        List<Author> all = getAll();
        ArrayList<String> idList = new ArrayList<>();

        for(Author author: all){
            idList.add(author.getId());
        }
        return idList;
    }
}
