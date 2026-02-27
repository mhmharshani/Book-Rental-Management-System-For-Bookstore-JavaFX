package service.custom.impl;

import model.dto.Payment;
import model.dto.RentNReturn;
import repository.RepositoryFactory;
import repository.custom.BookRepository;
import repository.custom.PaymentRepository;
import repository.custom.RentNReturnRepository;
import service.custom.RentNReturnService;
import util.RepositoryType;

import java.sql.SQLException;

public class RentNReturnServiceImpl implements RentNReturnService {

    RentNReturnRepository rentNReturnRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RENTNRETURN);
    PaymentRepository paymentRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.PAYMENT);

    @Override
    public boolean addRent(RentNReturn rent,Payment payment) throws SQLException {
        return rentNReturnRepositoryType.addRent(rent,payment);
    }
}
