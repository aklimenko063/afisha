package org.javaacademy.afisha.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.javaacademy.afisha.model.entity.EventType;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EventTypeProperty {
    MUSEUM("museum", 0),
    CINEMA("cinema", 100),
    THEATHER("theather", 50);

    private final String name;
    private final Integer countTickets;

    public static Integer getCountTickets(EventType eventType) {
        return Arrays.stream(EventTypeProperty.values())
                .filter(e -> e.getName().equals(eventType.getName()))
                .map(e -> e.countTickets)
                .findFirst()
                .orElse(0);
    }
}
