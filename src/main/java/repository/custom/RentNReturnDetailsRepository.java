package repository.custom;

import model.dto.Payment;
import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import repository.CrudRepository;
import repository.SuperRepository;

import java.sql.SQLException;
import java.util.List;

public interface RentNReturnDetailsRepository extends SuperRepository {
    boolean insertRentDetails(List<RentNReturnDetails> rentNReturnDetailsList) throws SQLException;
}
