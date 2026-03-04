package service.custom.impl;

import model.dto.Author;
import model.dto.User;
import model.dto.UserCredentials;
import model.dto.UserInfo;
import repository.RepositoryFactory;
import repository.SuperRepository;
import repository.custom.AuthorRepository;
import repository.custom.UserProfileRepository;
import service.custom.UserProfileService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class UserProfileServiceImpl implements UserProfileService {

    UserProfileRepository userProfileRepositoryType = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.USER);

    @Override
    public boolean addUser(User user) throws SQLException {
        return userProfileRepositoryType.create(user);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return userProfileRepositoryType.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return userProfileRepositoryType.deleteById(id);
    }

    @Override
    public User searchUserById(String id) throws SQLException {
        return userProfileRepositoryType.getById(id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<String> getAllUserIDs() throws SQLException {
        return List.of();
    }

    @Override
    public List<UserInfo> getAllUserInfo() throws SQLException {
        return userProfileRepositoryType.getAllUserInfo();
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) throws SQLException {
        return userProfileRepositoryType.createUserInfo(userInfo);
    }

    @Override
    public boolean addUsercredential(UserCredentials userCredentials) throws SQLException {
        return userProfileRepositoryType.createUserCredentials(userCredentials);
    }

    @Override
    public String generateNextUserId() throws SQLException {
        return userProfileRepositoryType.generateNextUserId();
    }

    @Override
    public String generateNextUserInfoId() throws SQLException {
        return userProfileRepositoryType.generateNextUserInfoId();
    }

    @Override
    public String generateNextUserCredentialId() throws SQLException {
        return userProfileRepositoryType.generateNextUserCredentialId();
    }

    @Override
    public UserInfo searchUserInfoByUserId(String id) throws SQLException {
        return userProfileRepositoryType.getUserInfoByUserId(id);
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) throws SQLException {
        return userProfileRepositoryType.update(userInfo);
    }
}
