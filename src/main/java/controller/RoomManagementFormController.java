package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.RoomDetails;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RoomManagementFormController implements Initializable {

    ObservableList <RoomDetails> roomDetails = FXCollections.observableArrayList();

    @FXML
    private JFXRadioButton availableRadio;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPricePerNight;

    @FXML
    private TableColumn<?, ?> colRoomNumber;

    @FXML
    private TableColumn<?, ?> colRoomStatus;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private JFXComboBox<String> roomTypeCombo;

    @FXML
    private TableView<RoomDetails> tblRoomDetails;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtPricePerNight;

    @FXML
    private JFXTextField txtRoomNumber;

    @FXML
    public JFXRadioButton maintenanceRadio;

    @FXML
    private JFXRadioButton unavailableRadio;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        int roomNumber = Integer.parseInt(txtRoomNumber.getText());
        String roomType = roomTypeCombo.getValue();
        double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        String description = txtDescription.getText();
        String roomStatus = checkRoomStatus();

        String SQL = "INSERT INTO rooms(room_number, room_type, price_per_night, description, room_status) VALUES(?,?,?,?,?);";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,roomNumber);
            preparedStatement.setObject(2,roomType);
            preparedStatement.setObject(3,pricePerNight);
            preparedStatement.setObject(4,description);
            preparedStatement.setObject(5,roomStatus);

            preparedStatement.executeUpdate();

            loadRoomDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtRoomNumber.setText(null);
        roomTypeCombo.getSelectionModel().clearSelection();
        txtPricePerNight.setText(null);
        txtDescription.setText(null);
        availableRadio.setSelected(false);
        unavailableRadio.setSelected(false);
        maintenanceRadio.setSelected(false);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        ------initialize roomTypeCombo-------
        ObservableList<String> roomTypes = FXCollections.observableArrayList(
                "Single",
                "Double",
                "Suite"
        );
        roomTypeCombo.setItems(roomTypes);
//        ----------------------------------------

//        ------set toggleGroup for radiobuttons------
        ToggleGroup roomStstusToggleGroup = new ToggleGroup();

        availableRadio.setToggleGroup(roomStstusToggleGroup);
        unavailableRadio.setToggleGroup(roomStstusToggleGroup);
//        --------------------------------------------

//        ------set table details----------
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));

        tblRoomDetails.setItems(loadRoomDetails());

//        ------------------------------------

    }

    private ObservableList<RoomDetails> loadRoomDetails(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM rooms;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                roomDetails.add(new RoomDetails(
                        resultSet.getInt("room_number"),
                        resultSet.getString("room_type"),
                        resultSet.getDouble("price_per_night"),
                        resultSet.getString("description"),
                        resultSet.getString("room_status")
                        )
                );
            }

//            System.out.println(roomDetails);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roomDetails;
    }

    private String checkRoomStatus(){
        if(availableRadio.isSelected()){
            return "Available";
        }else if(maintenanceRadio.isSelected()){
            return "Maintenance";
        }
        return "UnAvailable";
    }

}
