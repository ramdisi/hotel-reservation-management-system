package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.CustomerDetails;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerManagementFormController implements Initializable {

    private CustomerManagementController customerManagementController;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<CustomerDetails> tblRoomDetails;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerManagementController.addDetails(txtName.getText(),txtEmail.getText(),txtPhoneNumber.getText(),txtAddress.getText());
        loadTable();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearInputs();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerManagementController.deleteDetails(tblRoomDetails.getSelectionModel().getSelectedItem().getPhoneNo());
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerManagementController.updateDetails(txtName.getText(),txtEmail.getText(),txtPhoneNumber.getText(),txtAddress.getText(),tblRoomDetails.getSelectionModel().getSelectedItem().getPhoneNo());
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerManagementController = new CustomerManagementController();
        loadTable();
    }
    private void loadTable(){
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblRoomDetails.setItems(customerManagementController.getDetails());

    }
    private void clearInputs(){
        txtAddress.setText(null);txtEmail.setText(null);txtName.setText(null);txtPhoneNumber.setText(null);
    }
    private void setSelectedInputs(){
        CustomerDetails selectedItem = tblRoomDetails.getSelectionModel().getSelectedItem();
        txtAddress.setText(selectedItem.getAddress());
        txtEmail.setText(selectedItem.getEmail());
        txtName.setText(selectedItem.getName());
        txtPhoneNumber.setText(selectedItem.getPhoneNo());
    }

    public void tbl_onClick_selectCustomerDetails(MouseEvent mouseEvent) {
        setSelectedInputs();
    }
}
