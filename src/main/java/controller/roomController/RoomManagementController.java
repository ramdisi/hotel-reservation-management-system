package controller.roomController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RoomDetails;

import java.sql.*;

public class RoomManagementController implements RoomManagementService {
    public void addDetails(Integer roomNumber,String roomType,Double pricePerNight,String description,String roomStatus){
        String SQL = "INSERT INTO rooms(room_number, room_type, price_per_night, description, room_status) VALUES(?,?,?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,roomNumber);
            preparedStatement.setObject(2,roomType);
            preparedStatement.setObject(3,pricePerNight);
            preparedStatement.setObject(4,description);
            preparedStatement.setObject(5,roomStatus);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateDetails(Integer roomNumber,String roomType,Double pricePerNight,String description,String roomStatus){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update Rooms set room_type=?,price_per_night=?,description=?,room_status=? where room_number=?");
            preparedStatement.setString(1,roomType);
            preparedStatement.setDouble(2,pricePerNight);
            preparedStatement.setString(3,description);
            preparedStatement.setString(4,roomStatus);
            preparedStatement.setInt(5,roomNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDetails(String roomNumber){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.prepareStatement("delete from Rooms where room_number="+roomNumber).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<RoomDetails> getDetails(){
        ObservableList<RoomDetails> roomDetails = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
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
            return roomDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
