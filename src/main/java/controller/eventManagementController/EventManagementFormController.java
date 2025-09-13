package controller.eventManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;

import java.net.URL;
import java.util.ResourceBundle;

public class EventManagementFormController implements Initializable {

    @FXML
    private JFXButton btn_addEvent;

    @FXML
    private JFXButton btn_updateEvent;

    @FXML
    private JFXComboBox<String> cmb_hallBooked;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TableColumn<?, ?> col_eventID;

    @FXML
    private TableColumn<?, ?> col_from;

    @FXML
    private TableColumn<?, ?> col_hallBooked;

    @FXML
    private TableColumn<?, ?> col_to;

    @FXML
    private DatePicker date_from;

    @FXML
    private DatePicker date_to;

    @FXML
    private Label label_availability;

    @FXML
    private TableView<Event> tbl_event;

    @FXML
    private JFXTextArea txt_description;

    private Event selectedEvent;

    private EventManagementService eventManagementService;

    @FXML
    void btn_onAction_addEvent(ActionEvent event) {

    }

    @FXML
    void btn_onAction_cancelEvent(ActionEvent event) {

    }

    @FXML
    void btn_onAction_checkAvailability(ActionEvent event) {

    }

    @FXML
    void btn_onAction_clear(ActionEvent event) {

    }

    @FXML
    void btn_onAction_updateEvent(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventManagementService = new EventManagementController();
        ObservableList<String> halls = FXCollections.observableArrayList("Wedding Hall 1","Wedding Hall 2","Open Area","Roof Top Bar");
        cmb_hallBooked.setItems(halls);
        loadTable();
    }

    private void loadTable() {
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_eventID.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        col_from.setCellValueFactory(new PropertyValueFactory<>("from"));
        col_to.setCellValueFactory(new PropertyValueFactory<>("to"));
        col_hallBooked.setCellValueFactory(new PropertyValueFactory<>("hallBooked"));
        tbl_event.setItems(eventManagementService.getAllEvents());
    }
}

