package repository.custom.impl;

import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import repository.custom.RentNReturnDetailsRepository;
import repository.custom.RentNReturnRepository;
import util.CrudUtil;

import java.sql.SQLException;
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
}
