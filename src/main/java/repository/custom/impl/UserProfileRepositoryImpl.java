package repository.custom.impl;

import model.dto.Book;
import model.dto.User;
import model.dto.UserCredentials;
import model.dto.UserInfo;
import repository.custom.UserProfileRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileRepositoryImpl implements UserProfileRepository {

    @Override
    public boolean create(User user) throws SQLException {
        return CrudUtil.execute("INSERT INTO user VALUES (?,?,?)",
                user.getId(),
                user.getCreatedAt(),
                user.getIsActive()
        );
    }

    @Override
    public boolean update(User user) throws SQLException {
        return CrudUtil.execute("UPDATE User SET created_at=?, is_active=? WHERE user_id= ? ",
                user.getCreatedAt(),
                user.getIsActive(),
                user.getId()
        );
    }

    @Override
    public boolean update(UserInfo userInfo) throws SQLException {
        return CrudUtil.execute("UPDATE userInfo SET name=?, designation=?, phone_number=?, address=?, user_id=? WHERE user_info_id= ? ",
                userInfo.getName(),
                userInfo.getDesignation(),
                userInfo.getPhoneNumber(),
                userInfo.getAddress(),
                userInfo.getUserId(),
                userInfo.getId()
        );
    }

    @Override
    public boolean deleteById(String id) throws SQLException {
        Boolean isDeleted = (Boolean) (CrudUtil.execute("DELETE FROM UserCredentials WHERE user_id = ?", id)) && (Boolean)(CrudUtil.execute("DELETE FROM UserInfo WHERE user_id = ?",id));
        return isDeleted;
    }

    @Override
    public User getById(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM User WHERE user_id = ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist) {
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getBoolean(3)
            );

            System.out.println(user);

            return user;
        }
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<UserInfo> getAllUserInfo() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM UserInfo");
        ArrayList<UserInfo> userInfoList = new ArrayList<>();

        while(resultSet.next()){
            userInfoList.add(
                    new UserInfo(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );

        }
        System.out.println(userInfoList);
        return userInfoList;
    }

    @Override
    public boolean createUserInfo(UserInfo userInfo) throws SQLException {
        return CrudUtil.execute("INSERT INTO UserInfo VALUES (?,?,?,?,?,?)",
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getDesignation(),
                userInfo.getPhoneNumber(),
                userInfo.getAddress(),
                userInfo.getUserId()
        );
    }

    @Override
    public boolean createUserCredentials(UserCredentials userCredentials) throws SQLException {
        return CrudUtil.execute("INSERT INTO UserCredentials VALUES (?,?,?,?)",
                userCredentials.getId(),
                userCredentials.getUserName(),
                userCredentials.getPassword(),
                userCredentials.getUserId()
        );
    }

    @Override
    public String generateNextUserId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM User ORDER BY user_id DESC LIMIT 1");

        if(resultSet.next()){
            String userId = resultSet.getString(1);
            int number = Integer.parseInt(userId.substring(1));
            number++;
            return "U"+String.format("%03d",number);
        } else{
            return "U001";
        }
    }

    @Override
    public String generateNextUserInfoId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM UserInfo ORDER BY user_info_id DESC LIMIT 1");

        if(resultSet.next()){
            String userId = resultSet.getString(1);
            int number = Integer.parseInt(userId.substring(2));
            number++;
            return "UI"+String.format("%03d",number);
        } else{
            return "UI001";
        }
    }

    @Override
    public String generateNextUserCredentialId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM UserCredentials ORDER BY user_cred_id DESC LIMIT 1");

        if(resultSet.next()){
            String userId = resultSet.getString(1);
            int number = Integer.parseInt(userId.substring(2));
            number++;
            return "UC"+String.format("%03d",number);
        } else{
            return "UC001";
        }
    }

    @Override
    public UserInfo getUserInfoByUserId(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM userInfo WHERE user_id= ? ",id);
        Boolean isExist = resultSet.next();

        if(isExist) {
            UserInfo userInfo = new UserInfo(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );

            System.out.println(userInfo);

            return userInfo;
        }
        return null;
    }
}
