package repository.custom;

import model.dto.Customer;
import repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
}
