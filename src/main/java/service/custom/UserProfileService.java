package service.custom;

import model.dto.Author;
import model.dto.User;
import model.dto.UserCredentials;
import model.dto.UserInfo;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface UserProfileService extends SuperService {

    boolean addUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    User searchUserById(String id) throws SQLException;

    List<User> getAll() throws SQLException;

    List<String> getAllUserIDs() throws SQLException;

    List<UserInfo> getAllUserInfo() throws SQLException;

    boolean addUserInfo(UserInfo userInfo) throws SQLException;

    boolean addUsercredential(UserCredentials userCredentials) throws SQLException;

    String generateNextUserId() throws SQLException;

    String generateNextUserInfoId() throws SQLException;

    String generateNextUserCredentialId() throws SQLException;

    UserInfo searchUserInfoByUserId(String userId) throws SQLException;

    boolean updateUserInfo(UserInfo userInfo) throws SQLException;
}
