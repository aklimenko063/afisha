package org.javaacademy.afisha.mapper;

import org.javaacademy.afisha.model.dto.TicketDtoRq;
import org.javaacademy.afisha.model.dto.TicketDtoRs;
import org.javaacademy.afisha.model.entity.Event;
import org.javaacademy.afisha.model.entity.EventType;
import org.javaacademy.afisha.model.entity.Place;
import org.javaacademy.afisha.model.entity.Ticket;

public class TicketMapper {

    public static TicketDtoRs convertTicketToTicketDtoRs(Ticket ticket,
                                                         Event event,
                                                         EventType eventType,
                                                         Place place,
                                                         TicketDtoRq ticketDtoRq) {
        TicketDtoRs ticketDtoRs = new TicketDtoRs();
        ticketDtoRs.setId(ticket.getId());
        ticketDtoRs.setEventName(event.getName());
        ticketDtoRs.setEventTypeName(eventType.getName());
        ticketDtoRs.setPlaceName(place.getName());
        ticketDtoRs.setPlaceAddress(place.getAddress());
        ticketDtoRs.setPlaceCity(place.getCity());
        ticketDtoRs.setEventDate(event.getEventDate());
        ticketDtoRs.setPrice(ticket.getPrice());
        ticketDtoRs.setEmail(ticketDtoRq.getEmail());
        return ticketDtoRs;
    }
}
