package repository.custom.impl;

import model.dto.Book;
import model.dto.Customer;
import model.dto.RentNReturnDetails;
import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Book getById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book WHERE ISBN= ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist) {
            Book book = new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(6),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            );

            System.out.println(book);

            return book;
        }
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Book");
        ArrayList<Book> bookList = new ArrayList<>();

        while(resultSet.next()){
            bookList.add(
                    new Book(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(6),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5)
                    )
            );

        }
        System.out.println(bookList);
        return bookList;
    }

    @Override
    public boolean updateStock(List<RentNReturnDetails> rentNReturnDetailsList) throws SQLException {
        for(RentNReturnDetails rentDetails : rentNReturnDetailsList){
            boolean isUpdateStock = updateStock(rentDetails);
            if(!isUpdateStock){
                return false;
            }
        }
        return true;
    }

    public boolean updateStock(RentNReturnDetails rentNReturnDetails) throws SQLException{
        System.out.println("In update stock");
        return CrudUtil.execute("UPDATE book SET stock = stock-? WHERE ISBN = ?",rentNReturnDetails.getQty(),rentNReturnDetails.getBookId());
    }

}
