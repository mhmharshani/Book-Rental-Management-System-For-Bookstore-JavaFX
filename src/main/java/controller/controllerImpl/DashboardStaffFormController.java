package controller.controllerImpl;

import controller.FormController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.TM.CartTM;
import model.TM.CustomerTM;
import model.TM.ReturnDetailsTM;
import model.dto.Book;
import model.dto.Customer;
import model.dto.RentNReturn;
import model.dto.RentNReturnDetails;
import service.ServiceFactory;
import service.SuperService;
import service.custom.BookService;
import service.custom.CustomerService;
import service.custom.RentNReturnService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardStaffFormController implements Initializable, FormController {


    BookService bookServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);
    CustomerService customerServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    RentNReturnService rentNReturnServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.RENTNRETURN);

    @FXML
    private TableView tblReturn;

    @FXML
    private TableColumn colRentIdR;

    @FXML
    private TextField txtSearchISBNReturn;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ComboBox cmbBookId;

    @FXML
    private TableColumn colBookId;

    @FXML
    private TableColumn colBookIdR;

    @FXML
    private TableColumn colFinesR;

    @FXML
    private TableColumn colOverdueDaysR;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colQtyR;

    @FXML
    private TableColumn colTitle;

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
    private TextField txtCustId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchCustomerRent;

    @FXML
    private TextField txtSearchCustomerReturn;

    @FXML
    private TextField txtTotalFines;

    @FXML
    private TextField txtTotalQty;

    @FXML
    private TextField txtTotalRent;

    AnchorPane paneContainer;

    ArrayList<CartTM> cartTMArrayList = new ArrayList<>();
    RentNReturn confirmedRent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalRent.setCellValueFactory(new PropertyValueFactory<>("total"));

        colBookIdR.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colRentIdR.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colQtyR.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOverdueDaysR.setCellValueFactory(new PropertyValueFactory<>("overdueDays"));
        colFinesR.setCellValueFactory(new PropertyValueFactory<>("fine"));

        loadBookIDs();
        loadDateAndTime();

        tblReturn.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue) ->{

            System.out.println("Select record new value : "+newValue);

            assert newValue !=null;
            ReturnDetailsTM returnDetailsTM = (ReturnDetailsTM) newValue;
            txtSearchISBNReturn.setText(returnDetailsTM.getBookId());
        });

        txtQty.setText("1");
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/view/customer_form.fxml");
            System.out.println("this :"+this);

            assert resource != null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_form.fxml"));
            Parent parent = loader.load();
            CustomerFormController controller = loader.getController();
            System.out.println("controller : "+loader.getController());

            controller.setContainer(paneContainer);

            paneContainer.getChildren().clear();
            paneContainer.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        try {
            Book book = bookServiceType.searchBookById(cmbBookId.getValue().toString());
            System.out.println("cart book : "+book);
            cartTMArrayList.add(new CartTM(
                    lblRentId.getText(),
                    book.getId(),
                    book.getTitle(),
                    Integer.parseInt(txtQty.getText()),
                    book.getRentPrice()*Integer.parseInt(txtQty.getText())
            ));
            System.out.println("cart arrayList : "+cartTMArrayList);
            tblCart.setItems(FXCollections.observableArrayList(cartTMArrayList));
            calNetTotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnConfirmOrderOnAction(ActionEvent event) {
        ArrayList<RentNReturnDetails> orderDetailsArrayList = new ArrayList<>();

        cartTMArrayList.forEach(cartTM -> orderDetailsArrayList.add(new RentNReturnDetails(
                cartTM.getRentId(),
                cartTM.getBookId(),
                cartTM.getQty(),
                cartTM.getTotal(),
                null
        )));

        confirmedRent = new RentNReturn(
                lblRentId.getText(),
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                Double.parseDouble(txtTotalRent.getText()),
                false,
                txtCustId.getText(),
                "U002",
                orderDetailsArrayList
        );

        try {
            URL resource = this.getClass().getResource("/view/book_rental_form.fxml");
            System.out.println("this :"+this);

            assert resource != null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/book_rental_form.fxml"));
            Parent parent = loader.load();
            BookRentalFormController controller = loader.getController();
            System.out.println("controller : "+loader.getController());

            controller.setContainer(paneContainer);
            controller.setConfirmedRent(confirmedRent);

            paneContainer.getChildren().clear();
            paneContainer.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnAllOnAction(ActionEvent event) {
        try {
            String phoneNo = txtSearchCustomerReturn.getText();
            Customer customer = customerServiceType.searchCustomerByPhone(phoneNo);
            System.out.println("customer "+customer);
            List<RentNReturn> notReturnedRentList = rentNReturnServiceType.searchRentByCustId(customer.getId());
            System.out.println("notReturnedList : "+notReturnedRentList);
            notReturnedRentList.forEach(rentNReturn -> {
                try {
                    Boolean isUpdated = rentNReturnServiceType.updateReturnStatus(rentNReturn.getId());
                    if(isUpdated){
                        new Alert(Alert.AlertType.INFORMATION,"Return dates updated").show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchCustomerRentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerReturnOnAction(ActionEvent event) {
        String phoneNo = txtSearchCustomerReturn.getText();
        String bookId = txtSearchISBNReturn.getText();

        try {
            Customer customer = customerServiceType.searchCustomerByPhone(phoneNo);
            System.out.println("customer "+customer);
            List<RentNReturn> notReturnedRentList = rentNReturnServiceType.searchRentByCustId(customer.getId());
            System.out.println("notReturnedList : "+notReturnedRentList);
            ArrayList<ReturnDetailsTM> returnDetailsTmList = new ArrayList<>();
            notReturnedRentList.forEach(rentNReturn -> {
                rentNReturn.getRentDetailsList().forEach(rentNReturnDetails -> {
                    long overdueDays = (ChronoUnit.DAYS.between(rentNReturn.getDueDate(), LocalDate.now()));
                    if(overdueDays<0){
                        overdueDays = 0;
                    }

                    double fines = rentNReturnDetails.getQty() * overdueDays * 10.0;
                    returnDetailsTmList.add(new ReturnDetailsTM(
                            rentNReturnDetails.getBookId(),
                            rentNReturnDetails.getRentId(),
                            rentNReturnDetails.getQty(),
                            (int)overdueDays,
                            fines
                    ));
                });
            });
            tblReturn.setItems(FXCollections.observableArrayList(returnDetailsTmList));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBookIDs(){
        try {
            List<String> allBookIDs = bookServiceType.getAllBookIDs();
            cmbBookId.setItems(FXCollections.observableArrayList(allBookIDs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void calNetTotal(){
        Double total =0.0;
        Integer qty = 0;
        for(CartTM cartTM : cartTMArrayList){
            total+=cartTM.getTotal();
            qty += cartTM.getQty();
        }
        txtTotalRent.setText(total.toString());
        txtTotalQty.setText(qty.toString());
    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        lblDate.setText(sdf.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



}
