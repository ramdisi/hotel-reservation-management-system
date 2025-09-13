package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@ToString
@Getter
public class Event {
    private String ID,description,hallBooked;
    private LocalDate from,to;
}
