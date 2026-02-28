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
import java.time.LocalDate;
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
        String id = txtId.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNo.getText();
        String address = txtAddress.getText();

        Customer customer = new Customer(id,name,phoneNumber,address,null);

        System.out.println(customer);
        try {
            if (customerServiceType.addCustomer(customer)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Added").show();
                loadTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Added").show();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try{
            if(customerServiceType.deleteCustomer(txtId.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer NOT Deleted!").show();
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
            Customer customer = customerServiceType.searchCustomerById(txtSearch.getText());
            if(customer !=null){
                setTextToValues(customer);
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"No customer found.").show();

                txtId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtPhoneNo.setText("");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try{
            String id = txtId.getText();
            String name = txtName.getText();
            String phoneNumber = txtPhoneNo.getText();
            String address = txtAddress.getText();

            Customer customer = new Customer(id,name,phoneNumber,address,null);

            if(customerServiceType.updateCustomer(customer)){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
                loadTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Updated").show();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
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

    private void setTextToValues(Customer customer){
        if(customer !=null){
            txtId.setText(customer.getId());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtPhoneNo.setText(customer.getPhoneNumber());
        }
    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }

}
