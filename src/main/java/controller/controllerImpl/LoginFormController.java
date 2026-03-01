package controller.controllerImpl;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.dto.UserCredentials;
import service.ServiceFactory;
import service.custom.BookService;
import service.custom.UserLoginService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable, FormController {

    UserLoginService userLoginServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    private JFXCheckBox chkAdmin;

    @FXML
    private JFXCheckBox chkStaff;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;
    private AnchorPane paneContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chkStaff.setSelected(true);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        boolean isValidUser = false;
        try {
            isValidUser = userLoginServiceType.confirmUserCredentials(userName, password);
            if(isValidUser){
                if(chkStaff.isSelected()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu_form.fxml"));
                    Parent rootMain = loader.load();
                    MenuFormController controller = loader.getController();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(rootMain);
                    stage.setScene(scene);
                    stage.show();

                    AnchorPane paneContainer = controller.getPaneLoadContent();
//
                    loader = new FXMLLoader(getClass().getResource("/view/dashboard_staff_form.fxml"));
                    Parent root = loader.load();
                    paneContainer.getChildren().clear();
                    paneContainer.getChildren().add(root);

                }
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Your username or password is incorrect").show();

            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }
}
