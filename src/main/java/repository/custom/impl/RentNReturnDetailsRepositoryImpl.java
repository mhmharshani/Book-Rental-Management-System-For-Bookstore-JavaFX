package repository.custom.impl;

import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import repository.custom.RentNReturnDetailsRepository;
import repository.custom.RentNReturnRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentNReturnDetailsRepositoryImpl implements RentNReturnDetailsRepository {

    @Override
    public boolean insertRentDetails(List<RentNReturnDetails> rentNReturnDetailsList) throws SQLException {
        for(RentNReturnDetails rentNReturnDetails : rentNReturnDetailsList){
            boolean isInsertRentDetail = insertRentDetails(rentNReturnDetails);
            if (!isInsertRentDetail){
                return false;
            }
        }

        return true;
    }

    public boolean insertRentDetails(RentNReturnDetails rentNReturnDetails) throws SQLException {
        System.out.println("in rent detail insert");
        return CrudUtil.execute("INSERT INTO RentDetails VALUES (?,?,?,?,?)",
                rentNReturnDetails.getRentId(),
                rentNReturnDetails.getBookId(),
                rentNReturnDetails.getQty(),
                rentNReturnDetails.getTotal(),
                null
        );
    }

    @Override
    public List<RentNReturnDetails> searchDetailsByRentId(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM RentDetails WHERE rent_id= ? ",id);
        ArrayList<RentNReturnDetails> rentNReturnDetailsList = new ArrayList<>();

        while(resultSet.next()){
            rentNReturnDetailsList.add(new RentNReturnDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    (resultSet.getDate(5)==null)?null:resultSet.getDate(5).toLocalDate()
            ));
            System.out.println(resultSet.getString(2));
        }
        System.out.println(rentNReturnDetailsList);
        return rentNReturnDetailsList;
    }

    @Override
    public boolean updateReturnDate(String id) throws SQLException {
        return CrudUtil.execute("UPDATE RentDetails SET return_date=? WHERE rent_id= ? ",
                LocalDate.now(),
                id
        );
    }
}
