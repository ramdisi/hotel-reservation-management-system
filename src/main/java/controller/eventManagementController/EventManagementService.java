package controller.eventManagementController;

import javafx.collections.ObservableList;
import model.Event;

public interface EventManagementService {
    ObservableList<Event> getAllEvents();
}
