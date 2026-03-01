package repository.custom.impl;

import model.dto.Customer;
import model.dto.UserCredentials;
import repository.custom.UserLoginRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginRepositoryImpl implements UserLoginRepository {

    @Override
    public UserCredentials getUserByUserName(String userName) throws SQLException {
        //userName = userId
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM UserCredentials WHERE username = ? ",userName);
        Boolean isExist = resultSet.next();
        System.out.println("isExist :"+isExist);
        if(isExist){
            UserCredentials userCredential = new UserCredentials(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            System.out.println(userCredential);

            return userCredential;
        }
        return null;
    }
}
