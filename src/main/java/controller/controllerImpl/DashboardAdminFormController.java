package controller.controllerImpl;

import com.jfoenix.controls.JFXCheckBox;
import controller.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardAdminFormController implements Initializable, FormController {

    @FXML
    private JFXCheckBox RentalReturnReport;

    @FXML
    private JFXCheckBox chkRentalReport;

    @FXML
    private ComboBox cmbDurationRental;

    @FXML
    private ComboBox cmbDurationReturn;
    private AnchorPane paneContainer;

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnToStaffDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void cmbDurationRentalOnAction(ActionEvent event) {

    }

    @FXML
    void cmbDurationReturnOnAction(ActionEvent event) {

    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
