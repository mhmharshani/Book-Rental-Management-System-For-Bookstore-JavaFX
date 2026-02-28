package repository.custom;

import model.dto.Payment;
import model.dto.RentNReturn;
import repository.CrudRepository;
import repository.SuperRepository;

import java.sql.SQLException;
import java.util.List;

public interface RentNReturnRepository extends SuperRepository {

    boolean addRent(RentNReturn rent, Payment payment) throws SQLException;

    List<RentNReturn> getAll() throws SQLException;

    RentNReturn getById(String id) throws SQLException;

    List<RentNReturn> getByCustomerId(String id) throws SQLException;

    Boolean updateReturnStatus(String id) throws SQLException;

    public String generateID() throws SQLException;
}
