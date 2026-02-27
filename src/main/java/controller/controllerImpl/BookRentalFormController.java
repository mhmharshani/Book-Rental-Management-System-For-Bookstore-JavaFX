package controller.controllerImpl;

import com.jfoenix.controls.JFXTextField;
import controller.FormController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.dto.Payment;
import model.dto.RentNReturn;
import service.ServiceFactory;
import service.custom.BookService;
import service.custom.RentNReturnService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BookRentalFormController implements Initializable, FormController {

    @FXML
    private ComboBox cmbPaymentMethod;

    @FXML
    private TableColumn colCartId;

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
    private JFXTextField txtCartId;

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

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

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

}
