package controller.eventManagementController;

import javafx.collections.ObservableList;
import model.Event;

public interface EventManagementService {
    ObservableList<Event> getAllEvents();
    void deleteSelectedEvent(String eventID);
    void addEvent(Event event);
    void updateEvent(Event event);
}
