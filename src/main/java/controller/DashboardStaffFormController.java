package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardStaffFormController implements Initializable {

    @FXML
    private TableColumn colBookId;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotalRent;

    @FXML
    private Label lblCartId;

    @FXML
    private Label lblRentId;

    @FXML
    private Label lblReturnId;

    @FXML
    private TableView tblCart;

    @FXML
    private TextField txtCartId;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtOverdueDays;

    @FXML
    private TextField txtSearchCustomerRent;

    @FXML
    private TextField txtSearchCustomerReturn;

    @FXML
    private TextField txtSelectBooks;

    @FXML
    private TextField txtTotalFines;

    @FXML
    private TextField txtTotalQty;

    @FXML
    private TextField txtTotalRent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCalculateFinesOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintRecieptOnAction(ActionEvent event) {

    }

    @FXML
    void btnRentBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerRentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerReturnOnAction(ActionEvent event) {

    }

}
