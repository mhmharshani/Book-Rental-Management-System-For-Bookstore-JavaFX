package controller.controllerImpl;

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
import model.dto.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable,FormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPhoneNo;

    @FXML
    private TableView tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtKidDob;

    @FXML
    private JFXTextField txtKidName;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNo;

    @FXML
    private TextField txtSearch;

    CustomerService customerServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    private AnchorPane paneContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadTable();

        //Enable select a record from table directly
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue) ->{

            System.out.println("Select record new value : "+newValue);

            assert newValue !=null;
            setTextToValues((CustomerTM) newValue);
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
//        try{
//            Customer customer = customerServiceType.searchCustomerById(txtId.getText());
//            if(customer !=null){
//                setTextToValues(customer);
//            }
//            else{
//                new Alert(Alert.AlertType.INFORMATION,"No customer found.").show();
//
//                cmbTitle.setValue("");
//                txtName.setText("");
//                dateDob.setValue(null);
//                txtSalary.setText("");
//                txtAddress.setText("");
//                txtCity.setText("");
//                txtProvince.setText("");
//                txtPostalCode.setText("");
//            }
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable(){
        try{
            List<Customer> all = customerServiceType.getAll();

            ArrayList<CustomerTM> customerTMArrayList = new ArrayList<>();
            all.forEach(customer -> {
                customerTMArrayList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getPhoneNumber(),
                        customer.getAddress()
                ));
            });

            ObservableList<CustomerTM> observableList = FXCollections.observableArrayList(customerTMArrayList);
            tblCustomer.setItems(observableList);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(CustomerTM customerTm){
        if(customerTm !=null){
            txtId.setText(customerTm.getId());
            txtName.setText(customerTm.getName());
            txtAddress.setText(customerTm.getAddress());
            txtPhoneNo.setText(customerTm.getPhoneNumber());
        }
    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }

}
