package repository.custom.impl;

import model.dto.Customer;
import repository.custom.CustomerRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Customer getById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE id= ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist){
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    null
            );

            System.out.println(customer);

            return customer;
        }

        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerList = new ArrayList<>();

        while(resultSet.next()){
            customerList.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            null
                    )
            );

        }
        return customerList;
    }

    @Override
    public Customer getByPhoneNo(String phoneNo) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE phone_number= ? ", phoneNo);
        Boolean isExist = resultSet.next();

        if (isExist) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    null
            );

            System.out.println(customer);

            return customer;
        }

        return null;
    }
}
