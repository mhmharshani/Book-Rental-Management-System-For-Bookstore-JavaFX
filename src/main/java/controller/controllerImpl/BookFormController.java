package controller.controllerImpl;

import com.jfoenix.controls.JFXTextField;
import controller.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.TM.BookTM;
import model.dto.Book;
import service.ServiceFactory;
import service.custom.AuthorService;
import service.custom.BookService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookFormController implements Initializable, FormController {

    public ComboBox cmbAuthorId;
    @FXML
    private JFXTextField txtId;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colRentPrice;

    @FXML
    private TableColumn colStock;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tblBook;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXTextField txtRentPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtTitle;

    BookService bookServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);
    AuthorService authorServiceType = ServiceFactory.getInstance().getServiceType(ServiceType.AUTHOR);
    private AnchorPane paneContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colRentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        loadTable();

        //Enable select a record from table directly
        tblBook.getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue) ->{

            System.out.println("Select record new value : "+newValue);

            assert newValue !=null;
            setTextToValues((BookTM) newValue);
        });

        loadAuthorIDs();
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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void btnAddAuthorOnAction(ActionEvent actionEvent) {
    }

    public void cmbAuthorId(ActionEvent actionEvent) {
    }

    private void loadAuthorIDs(){

        try {
            List<String> allAuthorIDs = authorServiceType.getAllAuthorIDs();
            cmbAuthorId.setItems(FXCollections.observableArrayList(allAuthorIDs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTable(){
        try{
            List<Book> all = bookServiceType.getAll();
            System.out.println(all);
            ArrayList<BookTM> bookTMArrayList = new ArrayList<>();
            all.forEach(book -> {

                bookTMArrayList.add(new BookTM(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthorId(),
                        book.getCategory(),
                        book.getRentPrice(),
                        book.getStock()
                ));
            });

            System.out.println(bookTMArrayList);

            ObservableList<BookTM> observableList = FXCollections.observableArrayList(bookTMArrayList);
            tblBook.setItems(observableList);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(BookTM bookTm) {
        if(bookTm !=null){
            txtId.setText(bookTm.getId());
            txtTitle.setText(bookTm.getTitle());
            cmbAuthorId.setValue(bookTm.getAuthorId());
            txtCategory.setText(bookTm.getCategory());
            txtRentPrice.setText(bookTm.getRentPrice().toString());
            txtStock.setText(bookTm.getStock().toString());
        }
    }

    public void setContainer(AnchorPane container) {
        this.paneContainer = container;
    }
}
