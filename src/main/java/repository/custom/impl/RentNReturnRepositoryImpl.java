package repository.custom.impl;

import db.DBConnection;
import model.dto.Book;
import model.dto.Payment;
import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import repository.RepositoryFactory;
import repository.custom.BookRepository;
import repository.custom.PaymentRepository;
import repository.custom.RentNReturnDetailsRepository;
import repository.custom.RentNReturnRepository;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
                        boolean isStockUpdate = bookRepository.updateStock(rent.getRentDetailsList(),1);
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

    @Override
    public List<RentNReturn> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookRentalReturn");
        ArrayList<RentNReturn> rentNReturnList = new ArrayList<>();

        while(resultSet.next()){

//            java.sql.Array sqlArray = resultSet.getArray(8);
//            RentNReturnDetails[] detailArray = (RentNReturnDetails[]) sqlArray.getArray();
//            List<RentNReturnDetails> detailsList = Arrays.asList(detailArray);

            rentNReturnList.add(
                    new RentNReturn(
                            resultSet.getString(1),
                            resultSet.getDate(2).toLocalDate(),
                            resultSet.getDate(3).toLocalDate(),
                            resultSet.getDouble(4),
                            resultSet.getBoolean(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            null
//                            detailsList
                    )
            );

        }
        System.out.println(rentNReturnList);
        return rentNReturnList;
    }

    @Override
    public RentNReturn getById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookRentalReturn WHERE rent_id= ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist) {
            RentNReturn rentNReturn = new RentNReturn(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getDouble(4),
                    resultSet.getBoolean(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    null
            );

            System.out.println(rentNReturn);

            return rentNReturn;
        }
        return null;
    }

    @Override
    public List<RentNReturn> getByCustomerId(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookRentalReturn WHERE customer_id= ? AND is_all_returned = false",id);

        ArrayList<RentNReturn> rentNReturnList = new ArrayList<>();

        while(resultSet.next()){
            rentNReturnList.add(new RentNReturn(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getDouble(4),
                    resultSet.getBoolean(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    rentDetailsRepository.searchDetailsByRentId(resultSet.getString(1))
            ));

        }
        System.out.println(rentNReturnList);
        return rentNReturnList;
    }

    @Override
    public Boolean updateReturnStatus(String id) throws SQLException {
        return CrudUtil.execute("UPDATE BookRentalReturn SET is_all_returned=? WHERE rent_id= ? ",
                true,
                id
        );
    }

    @Override
    public String generateID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookRentalReturn ORDER BY rent_id DESC LIMIT 1");

        if(resultSet.next()){
            String rentId = resultSet.getString(1);
            int number = Integer.parseInt(rentId.substring(1));
            number++;
            return "R"+String.format("%04d",number);
        }

        else{
            return "R0001";
        }
    }
}
