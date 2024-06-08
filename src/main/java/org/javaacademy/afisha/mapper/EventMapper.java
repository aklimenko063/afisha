package org.javaacademy.afisha.mapper;

import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.EventDtoRs;
import org.javaacademy.afisha.model.entity.Event;
import org.javaacademy.afisha.model.entity.EventType;
import org.javaacademy.afisha.model.entity.Place;

public class EventMapper {

    public static Event covertEventDtoRqToEventEntity(EventDtoRq eventDtoRq, EventType eventType, Place place) {
        Event event = new Event();
        event.setName(eventDtoRq.getName());
        event.setEventTypeId(eventType.getId());
        event.setEventDate(eventDtoRq.getEventDate());
        event.setPlaceId(place.getId());
        return event;
    }

    public static EventDtoRs covertEventEntityToEventDtoRs(Event event, EventType eventType, Place place) {
        EventDtoRs eventDtoRs = new EventDtoRs();
        eventDtoRs.setName(event.getName());
        eventDtoRs.setEventTypeName(eventType.getName());
        eventDtoRs.setEventDate(event.getEventDate());
        eventDtoRs.setPlaceName(place.getName());
        return eventDtoRs;
    }
}
