package service.custom;

import model.dto.UserCredentials;
import service.SuperService;

import java.sql.SQLException;

public interface UserLoginService extends SuperService {

    boolean confirmUserCredentials(String userName,String password) throws SQLException;

    String getProfileName(String userName) throws SQLException;
}
