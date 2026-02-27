package repository.custom;

import model.dto.Payment;
import model.dto.RentNReturn;
import repository.CrudRepository;
import repository.SuperRepository;

import java.sql.SQLException;

public interface RentNReturnRepository extends SuperRepository {

    boolean addRent(RentNReturn rent, Payment payment) throws SQLException;
}
