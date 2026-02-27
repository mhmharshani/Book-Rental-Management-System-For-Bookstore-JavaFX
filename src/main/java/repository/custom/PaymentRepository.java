package repository.custom;

import model.dto.Customer;
import model.dto.Payment;
import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import repository.CrudRepository;
import repository.SuperRepository;

import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository extends SuperRepository {
    boolean insertPayment(Payment payment) throws SQLException;
}
