package service.custom.impl;

import model.dto.Payment;
import model.dto.RentNReturn;
import repository.RepositoryFactory;
import repository.SuperRepository;
import repository.custom.BookRepository;
import repository.custom.PaymentRepository;
import repository.custom.RentNReturnDetailsRepository;
import repository.custom.RentNReturnRepository;
import service.custom.RentNReturnService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class RentNReturnServiceImpl implements RentNReturnService {

    RentNReturnRepository rentNReturnRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RENTNRETURN);
    PaymentRepository paymentRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.PAYMENT);
    RentNReturnDetailsRepository rentNReturnDetailsRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RENTNRETURNDETAILS);

    @Override
    public boolean addRent(RentNReturn rent,Payment payment) throws SQLException {
        return rentNReturnRepositoryType.addRent(rent,payment);
    }

    @Override
    public List<RentNReturn> getAll() throws SQLException {
        return rentNReturnRepositoryType.getAll();
    }

    @Override
    public RentNReturn searchRentById(String id) throws SQLException {
        return rentNReturnRepositoryType.getById(id);
    }

    @Override
    public List<RentNReturn> searchRentByCustId(String id) throws SQLException {
        return rentNReturnRepositoryType.getByCustomerId(id);
    }

    @Override
    public Boolean updateReturnStatus(String id) throws SQLException {
        return (rentNReturnRepositoryType.updateReturnStatus(id)) && (rentNReturnDetailsRepositoryType.updateReturnDate(id));
    }
}
