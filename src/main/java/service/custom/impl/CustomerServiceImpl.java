package service.custom.impl;

import model.dto.Customer;
import service.custom.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return false;
    }

    @Override
    public Customer searchCustomerById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }
}
