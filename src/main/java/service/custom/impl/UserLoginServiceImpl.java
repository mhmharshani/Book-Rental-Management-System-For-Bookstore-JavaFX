package service.custom.impl;

import model.dto.UserCredentials;
import repository.RepositoryFactory;
import repository.custom.CustomerRepository;
import repository.custom.UserLoginRepository;
import service.custom.UserLoginService;
import util.RepositoryType;

import java.sql.SQLException;

public class UserLoginServiceImpl implements UserLoginService {

    UserLoginRepository userLoginRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.USER);

    @Override
    public boolean confirmUserCredentials(String userName, String password) throws SQLException {
        UserCredentials userCredential = userLoginRepositoryType.getUserByUserName(userName);
        if(password.equals(userCredential.getPassword())){
            return true;
        }
        return false;
    }
}
