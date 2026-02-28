package service.custom;

import model.dto.Book;
import model.dto.Payment;
import model.dto.RentNReturn;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface RentNReturnService extends SuperService {


    boolean addRent(RentNReturn rent, Payment payment) throws SQLException;


    List<RentNReturn> getAll() throws SQLException;
    RentNReturn searchRentById(String id) throws SQLException;

    List<RentNReturn> searchRentByCustId(String id) throws SQLException;

    Boolean updateReturnStatus(String id) throws SQLException;
}
