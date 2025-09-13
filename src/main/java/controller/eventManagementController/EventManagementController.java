package controller.eventManagementController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;

import java.sql.*;

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

    @Override
    public void deleteSelectedEvent(String eventID) {

    }

    @Override
    public void addEvent(Event event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.prepareStatement("select eventID from event order by eventID desc limit 1").executeQuery();
            String lastEventID;
            if (resultSet.next()){
                lastEventID=resultSet.getString("eventID");
            }else {
                lastEventID="E000";
            }
            String nextEventID=String.format("E%03d",Integer.parseInt(lastEventID.substring(1))+1);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into event values(?,?,?,?,?)");
            preparedStatement.setString(1,nextEventID);
            preparedStatement.setString(2,event.getDescription());
            preparedStatement.setString(3,event.getHallBooked());
            preparedStatement.setDate(4, Date.valueOf(event.getFrom()));
            preparedStatement.setDate(5,Date.valueOf(event.getTo()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEvent(Event event) {

    }
}
