package repository.custom;

import model.dto.Book;
import model.dto.User;
import model.dto.UserCredentials;
import model.dto.UserInfo;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface UserProfileRepository extends CrudRepository<User,String> {

    List<UserInfo> getAllUserInfo() throws SQLException;

    boolean createUserInfo(UserInfo userInfo) throws SQLException;

    boolean createUserCredentials(UserCredentials userCredentials) throws SQLException;

    String generateNextUserId() throws SQLException;

    String generateNextUserInfoId() throws SQLException;

    String generateNextUserCredentialId() throws SQLException;

    UserInfo getUserInfoByUserId(String userId) throws SQLException;

    boolean update(UserInfo userinfo) throws SQLException;
}
