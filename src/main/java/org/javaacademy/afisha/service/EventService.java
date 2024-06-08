package org.javaacademy.afisha.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.EventDtoRs;
import org.javaacademy.afisha.model.entity.Event;
import org.javaacademy.afisha.model.entity.EventType;
import org.javaacademy.afisha.model.entity.Place;
import org.javaacademy.afisha.model.entity.Ticket;
import org.javaacademy.afisha.mapper.EventMapper;
import org.javaacademy.afisha.repository.EventRepository;
import org.javaacademy.afisha.repository.EventTypeRepository;
import org.javaacademy.afisha.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final TicketService ticketService;
    private final TransactionTemplate transactionTemplate;

    public EventDtoRs addNewEvent(EventDtoRq eventDtoRq) {
        Place place = findPlaceByName(eventDtoRq.getPlaceName());
        EventType eventType = eventTypeRepository.findEntityByName(eventDtoRq.getEventTypeName()).orElseThrow();
        Event event = generateEvent(eventDtoRq, eventType, place);

        transactionTemplate.executeWithoutResult(transactionStatus -> {
            eventRepository.addNewEntity(event);
        });

        List<Ticket> tickets = ticketService.generateTickets(eventDtoRq);
        tickets.forEach(ticket -> ticketService.addNewTicketFromEvents(ticket));

        return EventMapper.covertEventEntityToEventDtoRs(
                event,
                eventTypeRepository.findEntityById(event.getEventTypeId()).orElseThrow(),
                placeRepository.findEntityById(event.getPlaceId()).orElseThrow());
    }

    public List<EventDtoRs> findAllEvents() {
        return eventRepository.findAllEntity().stream()
                .map((Event event) -> EventMapper.covertEventEntityToEventDtoRs(
                        event,
                        eventTypeRepository.findEntityById(event.getEventTypeId()).orElseThrow(),
                        placeRepository.findEntityById(event.getPlaceId()).orElseThrow()))
                .toList();
    }

    private Event generateEvent(EventDtoRq eventDtoRq, EventType eventType, Place place) {
        return EventMapper.covertEventDtoRqToEventEntity(eventDtoRq, eventType, place);
    }

    private Place findPlaceByName(String placeName) {
        return placeRepository.findPlaceByName(placeName).orElseThrow();
    }

}
