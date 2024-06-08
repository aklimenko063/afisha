package org.javaacademy.afisha.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.TicketDtoRq;
import org.javaacademy.afisha.model.dto.TicketDtoRs;
import org.javaacademy.afisha.enams.EventTypeProperty;
import org.javaacademy.afisha.model.entity.Event;
import org.javaacademy.afisha.model.entity.EventType;
import org.javaacademy.afisha.model.entity.Place;
import org.javaacademy.afisha.model.entity.Ticket;
import org.javaacademy.afisha.exeption.TicketNotFoundException;
import org.javaacademy.afisha.mapper.TicketMapper;
import org.javaacademy.afisha.repository.EventRepository;
import org.javaacademy.afisha.repository.EventTypeRepository;
import org.javaacademy.afisha.repository.PlaceRepository;
import org.javaacademy.afisha.repository.TicketRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;
    private final TransactionTemplate transactionTemplate;

    public void addNewTicketFromEvents(Ticket ticket) {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            ticketRepository.addNewEntityFromEvents(ticket);
        });
    }

    public void addNewTicket(Ticket ticket) {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            ticketRepository.addNewEntity(ticket);
        });
    }

    public void updateTicket(Ticket ticket, String email) {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            ticketRepository.updateTicket(ticket, email);
        });
    }

    public TicketDtoRs buyTicket(TicketDtoRq ticketDtoRq) {
        Event event = eventRepository.findEntityByName(ticketDtoRq.getEventName()).orElseThrow();
        EventType eventType = eventTypeRepository.findEntityById(event.getEventTypeId()).orElseThrow();
        Place place = placeRepository.findEntityById(event.getPlaceId()).orElseThrow();
        Ticket findingTicket;
        if (EventTypeProperty.getCountTickets(eventType) == 0) {
            Ticket ticket = createNewTicket(ZERO, event.getId(), ticketDtoRq.getEmail());
            addNewTicket(ticket);
            findingTicket = ticketRepository.findLastCreatedTicket(ticket, event).orElseThrow();
        } else {
            try {
                findingTicket = ticketRepository.findTicketByEvent(event).orElseThrow();
                updateTicket(findingTicket, ticketDtoRq.getEmail());
            } catch (EmptyResultDataAccessException e) {
                throw new TicketNotFoundException("Билетов нет!");
            }
        }
        return TicketMapper
                .convertTicketToTicketDtoRs(findingTicket, event, eventType, place, ticketDtoRq);
    }

    public List<Ticket> generateTickets(EventDtoRq eventDtoRq) {
        List<Ticket> tickets = new ArrayList<>();
        Event event = eventRepository.findEntityByName(eventDtoRq.getName()).orElseThrow();
        EventType eventType = eventTypeRepository.findEntityById(event.getEventTypeId()).orElseThrow();
        Integer countTickets = EventTypeProperty.getCountTickets(eventType);
        IntStream.range(0, countTickets).forEach(e -> {
            tickets.add(createNewTicket(eventDtoRq.getPrice(), event.getId()));
        });
        return tickets;
    }

    public Ticket createNewTicket(BigDecimal price, Integer eventId) {
        Ticket ticket = new Ticket();
        ticket.setEventId(eventId);
        ticket.setPrice(price);
        return ticket;
    }

    public Ticket createNewTicket(BigDecimal price, Integer eventId, String email) {
        Ticket ticket = new Ticket();
        ticket.setEventId(eventId);
        ticket.setPrice(price);
        ticket.setClientEmail(email);
        ticket.setSelled(true);
        return ticket;
    }

}
