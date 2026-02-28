package repository.custom;

import model.dto.Book;
import model.dto.RentNReturnDetails;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository extends CrudRepository<Book,String> {
    boolean updateStock(List<RentNReturnDetails> rentNReturnDetailsList, int plusMinusValue) throws SQLException;
}
