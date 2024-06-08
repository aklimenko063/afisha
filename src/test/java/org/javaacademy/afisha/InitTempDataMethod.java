package org.javaacademy.afisha;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.model.dto.TicketDtoRq;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class InitTempDataMethod {
    public static final String BASE_URL = "/api/v1";

    public List<PlaceDto> createPlaces() {
        PlaceDto museum = new PlaceDto();
        museum.setName("Музей");
        museum.setAddress("Музей адрес");
        museum.setCity("Музей город");

        PlaceDto theather = new PlaceDto();
        theather.setName("Театр");
        theather.setAddress("Театр адрес");
        theather.setCity("Театр город");

        PlaceDto cinema = new PlaceDto();
        cinema.setName("Кинотеатр");
        cinema.setAddress("Кинотеатр адрес");
        cinema.setCity("Кинотеатр город");

        return List.of(museum, theather, cinema);
    }

    public void addNewPlace(PlaceDto placeDto, String urlPostfix) {
        RestAssured
                .given()
                .body(placeDto)
                .contentType(ContentType.JSON)
                .log().all()
                .post(BASE_URL + urlPostfix)
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    public List<EventDtoRq> createEvents() {
        EventDtoRq eventMuseum = new EventDtoRq();
        eventMuseum.setName("Музей");
        eventMuseum.setEventDate(LocalDateTime.of(
                LocalDate.of(2024, 01, 01),
                LocalTime.of(12, 00, 00)));
        eventMuseum.setPrice(BigDecimal.valueOf(100));
        eventMuseum.setEventTypeName("museum");
        eventMuseum.setPlaceName("Музей");

        EventDtoRq eventTheather = new EventDtoRq();
        eventTheather.setName("Театр");
        eventTheather.setEventDate(LocalDateTime.of(
                LocalDate.of(2024, 02, 01),
                LocalTime.of(12, 00, 00)));
        eventTheather.setPrice(BigDecimal.valueOf(100));
        eventTheather.setEventTypeName("theather");
        eventTheather.setPlaceName("Театр");

        EventDtoRq eventCinema = new EventDtoRq();
        eventCinema.setName("Кинотеатр");
        eventCinema.setEventDate(LocalDateTime.of(
                LocalDate.of(2024, 02, 03),
                LocalTime.of(12, 00, 00)));
        eventCinema.setPrice(BigDecimal.valueOf(100));
        eventCinema.setEventTypeName("cinema");
        eventCinema.setPlaceName("Кинотеатр");

        return List.of(eventMuseum, eventTheather, eventCinema);
    }

    public void addNewEvent(EventDtoRq eventDtoRq, String urlPostfix) {
        RestAssured
                .given()
                .body(eventDtoRq)
                .contentType(ContentType.JSON)
                .log().all()
                .post(BASE_URL + urlPostfix)
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    public List<TicketDtoRq> createTicketDtoRq() {
        TicketDtoRq ticketDtoRqCinema = new TicketDtoRq();
        ticketDtoRqCinema.setEventName("Кинотеатр");
        ticketDtoRqCinema.setEmail("test@mail.xyz");

        TicketDtoRq ticketDtoRqMuseum = new TicketDtoRq();
        ticketDtoRqMuseum.setEventName("Музей");
        ticketDtoRqMuseum.setEmail("test@mail.xyz");

        TicketDtoRq ticketDtoRqTheather = new TicketDtoRq();
        ticketDtoRqTheather.setEventName("Театр");
        ticketDtoRqTheather.setEmail("test@mail.xyz");

        return List.of(ticketDtoRqCinema, ticketDtoRqMuseum, ticketDtoRqTheather);
    }

    public void buyTicketSuccess(TicketDtoRq ticketDtoRq, String urlPostfix) {
        RestAssured
                .given()
                .body(ticketDtoRq)
                .contentType(ContentType.JSON)
                .log().all()
                .post(BASE_URL + urlPostfix)
                .then()
                .log().all()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    public void buyTicketFailed(TicketDtoRq ticketDtoRq, String urlPostfix) {
        RestAssured
                .given()
                .body(ticketDtoRq)
                .contentType(ContentType.JSON)
                .log().all()
                .post(BASE_URL + urlPostfix)
                .then()
                .log().all()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
    }
}
