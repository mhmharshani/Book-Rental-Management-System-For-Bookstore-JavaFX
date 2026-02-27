package repository.custom.impl;

import com.mysql.cj.protocol.Resultset;
import model.dto.Payment;
import model.dto.RentNReturn;
import repository.custom.PaymentRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public boolean insertPayment(Payment payment) throws SQLException {
        System.out.println("in payment insert");
        return CrudUtil.execute("INSERT INTO Payment VALUES (?,?,?,?,?)",
                generateID(),
                payment.getRentId(),
                payment.getMethod(),
                payment.getBillAmount(),
                payment.getStatus()
        );
    }
    
    public String generateID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment ORDER BY ref_id DESC LIMIT 1");

        if(resultSet.next()){
            String refId = resultSet.getString(1);
            int number = Integer.parseInt(refId.substring(3));
            number++;
            return "Ref"+String.format("%05d",number);
        }

        else{
            return "Ref00001";
        }
    }
}
