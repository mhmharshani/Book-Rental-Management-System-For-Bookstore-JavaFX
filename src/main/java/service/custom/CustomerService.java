package service.custom;

import model.dto.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    boolean addCustomer(Customer customer) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    Customer searchCustomerById(String id) throws SQLException;

    List<Customer> getAll() throws SQLException;

}
