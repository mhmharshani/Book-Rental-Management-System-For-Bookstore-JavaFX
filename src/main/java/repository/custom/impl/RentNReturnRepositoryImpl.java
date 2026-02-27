package repository.custom.impl;

import db.DBConnection;
import model.dto.Payment;
import model.dto.RentNReturn;
import repository.RepositoryFactory;
import repository.custom.BookRepository;
import repository.custom.PaymentRepository;
import repository.custom.RentNReturnDetailsRepository;
import repository.custom.RentNReturnRepository;
import util.RepositoryType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RentNReturnRepositoryImpl implements RentNReturnRepository {

    BookRepository bookRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);
    PaymentRepository paymentRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.PAYMENT);
    RentNReturnDetailsRepository rentDetailsRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RENTNRETURNDETAILS);

    @Override
    public boolean addRent(RentNReturn rent, Payment payment) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            connection.setAutoCommit(false);
            PreparedStatement psTM = connection.prepareStatement("INSERT INTO bookRentalReturn VALUES (?,?,?,?,?,?,?)");
            psTM.setString(1, rent.getId());
            psTM.setObject(2,rent.getIssueDate());
            psTM.setObject(3,rent.getDueDate());
            psTM.setDouble(4,rent.getTotal());
            psTM.setBoolean(5,rent.getIsAllReturned());
            psTM.setString(6, rent.getCustomerId());
            psTM.setString(7, rent.getUserId());

            boolean isRentInsert = psTM.executeUpdate() > 0;
            System.out.println("In rent insert");
            if(isRentInsert){
                boolean isPaymentInsert = paymentRepository.insertPayment(payment);
                if(isPaymentInsert){
                    boolean isRentDetailsInsert = rentDetailsRepository.insertRentDetails(rent.getRentDetailsList());
                    if(isRentDetailsInsert){
                        boolean isStockUpdate = bookRepository.updateStock((rent.getRentDetailsList()));
                        if(isStockUpdate){
                            connection.commit();
                            System.out.println("commited");
                            return true;
                        }
                    }
                }

            }
            connection.rollback();
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
