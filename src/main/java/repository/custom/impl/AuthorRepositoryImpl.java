package repository.custom.impl;

import model.dto.Author;
import model.dto.Customer;
import repository.custom.AuthorRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public boolean create(Author author) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Author author) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(String s) throws SQLException {
        return false;
    }

    @Override
    public Author getById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM author WHERE author_id= ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist){
            Author author = new Author(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            System.out.println(author);
            return author;
        }
        return null;
    }

    @Override
    public List<Author> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Author");
        ArrayList<Author> authorList = new ArrayList<>();

        while(resultSet.next()){
            authorList.add(
                    new Author(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );

        }
        return authorList;
    }

}
