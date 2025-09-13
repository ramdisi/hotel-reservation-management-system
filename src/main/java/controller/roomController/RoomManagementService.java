package controller.roomController;

import javafx.collections.ObservableList;
import model.RoomDetails;

public interface RoomManagementService {
    ObservableList<RoomDetails> getDetails();
    void updateDetails(Integer roomNumber,String roomType,Double pricePerNight,String description,String roomStatus);
    void addDetails(Integer roomNumber,String roomType,Double pricePerNight,String description,String roomStatus);
    void deleteDetails(String roomNumber);
}
