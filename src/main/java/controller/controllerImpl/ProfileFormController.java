package controller.controllerImpl;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.TM.CustomerTM;
import model.TM.UserInfoTM;
import model.dto.*;
import service.ServiceFactory;
import service.custom.BookService;
import service.custom.UserProfileService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable, FormController {
    @FXML
    private JFXTextField txtPhoneNumber;
    
    @FXML
    private TableColumn colPhoneNumber;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtCurrentPassword;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colDesignation;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableView tblUser;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtDesignation;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtSearch;

    private AnchorPane paneContainer;

    UserProfileService userProfileServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadTable();

        //Enable select a record from table directly
        tblUser.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue) ->{

            System.out.println("Select record new value : "+newValue);

            assert newValue !=null;
            setTextToValues((UserInfoTM) newValue);
        });


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String name = txtName.getText();
        String designation = txtDesignation.getText();
        String address = txtAddress.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String userId = txtId.getText();

        String userInfoId = null;
        String userCredentialId = null;

        try {
            userInfoId = userProfileServiceType.generateNextUserInfoId();
            userCredentialId = userProfileServiceType.generateNextUserCredentialId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        User user = new User(userId, LocalDate.now(), true);

        UserInfo userInfo = new UserInfo(userInfoId, name, designation, phoneNumber, address, userId);

        UserCredentials userCredentials = new UserCredentials(userCredentialId,user.getId(), (userId+"@"+name), userId);

        System.out.println(userInfo);
        try {
            if (userProfileServiceType.addUser(user)) {
                if(userProfileServiceType.addUserInfo(userInfo)){
                    if(userProfileServiceType.addUsercredential(userCredentials)){
                        new Alert(Alert.AlertType.INFORMATION, "User Added").show();
                        System.out.println("Username is your userId while password is in (userId+@+name) format. Once you login for the first time, make sure to change the password!");
                        loadTable();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Added").show();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        //Only delete user (personal) information and credentials
        try{
            if(userProfileServiceType.deleteUser(txtId.getText())){
                User user = userProfileServiceType.searchUserById(txtId.getText());
                user.setIsActive(false);
                if(userProfileServiceType.updateUser(user)){
                    new Alert(Alert.AlertType.INFORMATION,"User Deleted!").show();
                    loadTable();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "User NOT Deleted!").show();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try{
            User user = userProfileServiceType.searchUserById(txtSearch.getText());
            if(user !=null){
                if(user.getIsActive()){
                    UserInfo userInfo = userProfileServiceType.searchUserInfoByUserId(user.getId());
                    setTextToValues(userInfo);
                }
                else{
                    new Alert(Alert.AlertType.INFORMATION,"User NOT in active state").show();
                }
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"No user found.").show();

                txtId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtDesignation.setText("");
                txtPhoneNumber.setText("");

                txtSearch.setText("");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try{

            String name = txtName.getText();
            String designation = txtDesignation.getText();
            String address = txtAddress.getText();
            String phoneNumber = txtPhoneNumber.getText();
            String userId = txtId.getText();

            UserInfo userInfo = userProfileServiceType.searchUserInfoByUserId(userId);
            userInfo = new UserInfo(userInfo.getId(), name, designation, phoneNumber, address, userId);

            if(userProfileServiceType.updateUserInfo(userInfo)){
                new Alert(Alert.AlertType.INFORMATION,"User Updated").show();
                loadTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"User Not Updated").show();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void loadTable(){
        try{

            List<UserInfo> all = userProfileServiceType.getAllUserInfo();

            ArrayList<UserInfoTM> userInfoTMArrayList = new ArrayList<>();
            all.forEach(userInfo -> {
                userInfoTMArrayList.add(new UserInfoTM(
                        userInfo.getName(),
                        userInfo.getDesignation(),
                        userInfo.getAddress(),
                        userInfo.getPhoneNumber(),
                        userInfo.getUserId()
                ));
            });

            ObservableList<UserInfoTM> observableList = FXCollections.observableArrayList(userInfoTMArrayList);
            tblUser.setItems(observableList);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(UserInfoTM userInfoTm){
        if(userInfoTm !=null){
            txtId.setText(userInfoTm.getUserId());
            txtName.setText(userInfoTm.getName());
            txtAddress.setText(userInfoTm.getAddress());
            txtDesignation.setText(userInfoTm.getDesignation());
            txtPhoneNumber.setText(userInfoTm.getPhoneNumber());
        }
    }

    private void setTextToValues(UserInfo userInfo){
        if(userInfo !=null){
            txtId.setText(userInfo.getUserId());
            txtName.setText(userInfo.getName());
            txtAddress.setText(userInfo.getAddress());
            txtDesignation.setText(userInfo.getDesignation());
            txtPhoneNumber.setText(userInfo.getPhoneNumber());
        }
    }


    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }


    public void btnGenerateIdOnAction(ActionEvent actionEvent) {
        try {
            String userId = userProfileServiceType.generateNextUserId();
            txtId.setText(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
