package controller.eventManagementController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventManagementController implements EventManagementService{
    @Override
    public ObservableList<Event> getAllEvents() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.prepareStatement("select * from event").executeQuery();
            ObservableList<Event> reternableList = FXCollections.observableArrayList();
            while (resultSet.next()){
                reternableList.add(new Event(
                        resultSet.getString("eventID"),
                        resultSet.getString("description"),
                        resultSet.getString("hallBooked"),
                        resultSet.getDate("begin").toLocalDate(),
                        resultSet.getDate("end").toLocalDate()
                ));
            }
            return reternableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
