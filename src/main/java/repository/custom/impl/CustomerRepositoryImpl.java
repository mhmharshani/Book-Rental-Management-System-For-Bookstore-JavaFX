package repository.custom.impl;

import model.dto.Customer;
import repository.custom.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(String s) throws SQLException {
        return false;
    }

    @Override
    public Customer getById(String s) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }
}
