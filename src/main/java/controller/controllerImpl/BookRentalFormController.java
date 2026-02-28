package controller.controllerImpl;

import com.jfoenix.controls.JFXTextField;
import controller.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.TM.BookTM;
import model.TM.RentNReturnTM;
import model.dto.Book;
import model.dto.Payment;
import model.dto.RentNReturn;
import service.ServiceFactory;
import service.custom.BookService;
import service.custom.RentNReturnService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class BookRentalFormController implements Initializable, FormController {

    @FXML
    private ComboBox cmbPaymentMethod;

    @FXML
    private TableColumn colCustId;

    @FXML
    private TableColumn colDueDate;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colIssueDate;

    @FXML
    private TableColumn colReturnStatus;

    @FXML
    private TableColumn colTotal;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private TableView tblRentNReturn;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtId;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txttotal;

    private AnchorPane paneContainer;

    private RentNReturn rent;

    RentNReturnService rentNReturnServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.RENTNRETURN);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colReturnStatus.setCellValueFactory(new PropertyValueFactory<>("returnStatus"));

        loadTable();

        tblRentNReturn.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue) ->{

            System.out.println("Select record new value : "+newValue);

            assert newValue !=null;
            setTextToValues((RentNReturnTM) newValue);
        });

        cmbPaymentMethod.setItems(FXCollections.observableArrayList(Arrays.asList("By Cash","By Card")));
        cmbPaymentMethod.setValue("By Cash");
    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) throws SQLException {
        loadText();

        Payment payment = new Payment(
                null,
                rent.getId(),
                cmbPaymentMethod.getValue().toString(),
                rent.getTotal(),
                "Successfull"
        );

        try{
            if(rentNReturnServiceType.addRent(rent,payment)){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Order Not Placed!").show();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnPrintRecieptOnAction (ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        loadTable();

        txtId.setText("");
        txtCustId.setText("");
        txttotal.setText("");
        dpIssueDate.setValue(null);
        dpDueDate.setValue(null);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try{
            RentNReturn rentNReturn = rentNReturnServiceType.searchRentById(txtSearch.getText());
            if(rentNReturn!=null){
                setTextToValues(rentNReturn);
                loadTableBySearch(rentNReturn);
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"No Rent Record found.").show();

                txtId.setText("");
                txtCustId.setText("");
                txttotal.setText("");
                dpIssueDate.setValue(null);
                dpDueDate.setValue(null);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbPaymentMethodOnAction(ActionEvent event) {

    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
        System.out.println("In set method : "+paneContainer);
    }

    public void setConfirmedRent(RentNReturn rent){
        this.rent = rent;
        System.out.println("In set rent method : "+rent);
    }

    public void loadText(){
        System.out.println("load Text rent :"+ rent);
        if(rent!=null){
            txtId.setText(rent.getId());
            txtCustId.setText(rent.getCustomerId());
            txttotal.setText(rent.getTotal().toString());
            dpIssueDate.setValue(rent.getIssueDate());
            dpDueDate.setValue(rent.getDueDate());
        }
    }

    private void loadTable(){
        try{
            List<RentNReturn> all = rentNReturnServiceType.getAll();
            System.out.println(all);
            ArrayList<RentNReturnTM> rentTMArrayList = new ArrayList<>();
            all.forEach(rentNreturn -> {

                rentTMArrayList.add(new RentNReturnTM(
                        rentNreturn.getId(),
                        rentNreturn.getCustomerId(),
                        rentNreturn.getTotal(),
                        rentNreturn.getIssueDate(),
                        rentNreturn.getDueDate(),
                        (rentNreturn.getIsAllReturned())?"All books Returned":"Pending"
                ));
            });

            System.out.println(rentTMArrayList);

            ObservableList<RentNReturnTM> observableList = FXCollections.observableArrayList(rentTMArrayList);
            tblRentNReturn.setItems(observableList);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(RentNReturnTM rentNReturnTm){
        if(rentNReturnTm !=null){
            txtId.setText(rentNReturnTm.getId());
            txtCustId.setText(rentNReturnTm.getCustomerId());
            txttotal.setText(rentNReturnTm.getTotal().toString());
            dpIssueDate.setValue(rentNReturnTm.getIssueDate());
            dpDueDate.setValue(rentNReturnTm.getDueDate());
        }
    }

    private void setTextToValues(RentNReturn rentNReturn){
        if(rentNReturn !=null){
            txtId.setText(rentNReturn.getId());
            txtCustId.setText(rentNReturn.getCustomerId());
            txttotal.setText(rentNReturn.getTotal().toString());
            dpIssueDate.setValue(rentNReturn.getIssueDate());
            dpDueDate.setValue(rentNReturn.getDueDate());
        }
    }

    private void loadTableBySearch(RentNReturn rentNReturn){
        ArrayList<RentNReturnTM> rentTMArrayList = new ArrayList<>();
        rentTMArrayList.add(new RentNReturnTM(
            rentNReturn.getId(),
            rentNReturn.getCustomerId(),
            rentNReturn.getTotal(),
            rentNReturn.getIssueDate(),
            rentNReturn.getDueDate(),
            (rentNReturn.getIsAllReturned())?"All books Returned":"Pending"
        ));

        System.out.println(rentTMArrayList);

        ObservableList<RentNReturnTM> observableList = FXCollections.observableArrayList(rentTMArrayList);
        tblRentNReturn.setItems(observableList);

    }

}
