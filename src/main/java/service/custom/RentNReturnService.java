package service.custom;

import model.dto.Payment;
import model.dto.RentNReturn;
import service.SuperService;

import java.sql.SQLException;

public interface RentNReturnService extends SuperService {


    boolean addRent(RentNReturn rent, Payment payment) throws SQLException;


}
