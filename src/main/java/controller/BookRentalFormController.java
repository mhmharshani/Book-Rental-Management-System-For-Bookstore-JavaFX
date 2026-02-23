package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BookRentalFormController implements Initializable {

    @FXML
    private TableColumn colCartId;

    @FXML
    private TableColumn colCustId;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colIssueDate;

    @FXML
    private TableColumn colReturnDate;

    @FXML
    private TableColumn colTotal;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private TableView tblRentNReturn;

    @FXML
    private JFXTextField txtCartId;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtId;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txttotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
