package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.RoomDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomManagementFormController implements Initializable {

    RoomManagementController roomManagementController;

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
        roomManagementController.addDetails(roomNumber,roomType,pricePerNight,description,roomStatus);
        loadRoomDetails();

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
        roomManagementController.deleteDetails(txtRoomNumber.getText());
        loadRoomDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int roomNumber = Integer.parseInt(txtRoomNumber.getText());
        String roomType = roomTypeCombo.getValue();
        Double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        String description = txtDescription.getText();
        String roomStatus = checkRoomStatus();
        roomManagementController.updateDetails(roomNumber,roomType,pricePerNight,description,roomStatus);
        loadRoomDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomManagementController = new RoomManagementController();

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
        loadRoomDetails();


    }

    private void loadRoomDetails(){
        roomDetails.clear();
        roomDetails=roomManagementController.getDetails();
        tblRoomDetails.setItems(roomDetails);
    }

    private String checkRoomStatus(){
        if(availableRadio.isSelected()){
            return "Available";
        }else if(maintenanceRadio.isSelected()){
            return "Maintenance";
        }
        return "UnAvailable";
    }
    @FXML
    void tbl_onClick_selectRoomDetails(MouseEvent event) {
        RoomDetails selectedItem = tblRoomDetails.getSelectionModel().getSelectedItem();
        txtRoomNumber.setText(String.valueOf(selectedItem.getRoomNumber()));
        txtDescription.setText(selectedItem.getDescription());
        txtPricePerNight.setText(String.valueOf(selectedItem.getPricePerNight()));
        roomTypeCombo.setValue(selectedItem.getRoomType());
        if (selectedItem.getRoomStatus().equals("Available")){
            availableRadio.setSelected(true);
        }else if (selectedItem.getRoomStatus().equals("Maintenance")){
            maintenanceRadio.setSelected(true);
        }else {
            unavailableRadio.setSelected(true);
        }
    }
}
