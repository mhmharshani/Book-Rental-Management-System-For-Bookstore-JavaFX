package repository.custom;

import model.dto.UserCredentials;
import repository.SuperRepository;

import java.sql.SQLException;

public interface UserLoginRepository extends SuperRepository {

    UserCredentials getUserByUserName(String userName) throws SQLException;

    String getProfileName(String userName) throws SQLException;
}
