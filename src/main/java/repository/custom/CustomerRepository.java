package repository.custom;

import model.dto.Customer;
import repository.CrudRepository;

import java.sql.SQLException;

public interface CustomerRepository extends CrudRepository<Customer,String> {
    Customer getByPhoneNo(String phoneNo) throws SQLException;
}
