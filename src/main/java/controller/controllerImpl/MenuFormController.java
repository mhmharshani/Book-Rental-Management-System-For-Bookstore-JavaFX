package controller.controllerImpl;

import controller.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuFormController implements Initializable, FormController{

    @FXML
    private Label lblHelloUser;

    @FXML
    private AnchorPane paneLoadContent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnBooksOnAction(ActionEvent event) {
        loadTab("/view/book_form.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        loadTab("/view/customer_form.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        loadTab("/view/dashboard_staff_form.fxml");
    }

    @FXML
    void btnProfileOnAction(ActionEvent event) {
        loadTab("/view/profile_form.fxml");
    }

    @FXML
    void btnRentalsReturnOnAction(ActionEvent event) {
        loadTab("/view/book_rental_form.fxml");
    }

    private void loadTab(String url){
        try {
            URL resource = this.getClass().getResource(url);
            System.out.println("this :"+this);

            assert resource != null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent parent = loader.load();
            FormController controller = loader.getController();
            System.out.println("controller : "+loader.getController());

            controller.setContainer(paneLoadContent);

            paneLoadContent.getChildren().clear();
            paneLoadContent.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setContainer(AnchorPane container) {
        this.paneLoadContent = container;
    }

//    private void loadView(String fxmlFile) throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//        Parent load = loader.load();
//        Object controller = loader.getController();
//        controller.setContainer(paneLoadContent);
//
//        assert resource != null;
//
//        Parent parent = FXMLLoader.load(resource);
//
//    }

}
