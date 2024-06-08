package org.javaacademy.afisha;

import io.restassured.RestAssured;
import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.javaacademy.afisha.InitTempDataMethod.BASE_URL;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class EventRestControllerTest {
	public final String URL_POSTFIX = "/event";
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private InitTempDataMethod initTempDataMethod;


    @Test
	void addNewEventSuccess() {
		List<PlaceDto> places = initTempDataMethod.createPlaces();
		places.forEach(placeDto -> initTempDataMethod.addNewPlace(placeDto, "/place"));
		List<EventDtoRq> events = initTempDataMethod.createEvents();
		events.forEach(eventDtoRq -> initTempDataMethod.addNewEvent(eventDtoRq, URL_POSTFIX));
	}

	@Test
	void findAllEventsSuccess() {
		List<PlaceDto> places = initTempDataMethod.createPlaces();
		places.forEach(placeDto -> initTempDataMethod.addNewPlace(placeDto,"/place"));
		List<EventDtoRq> events = initTempDataMethod.createEvents();
		events.forEach(eventDtoRq -> initTempDataMethod.addNewEvent(eventDtoRq, URL_POSTFIX));
		RestAssured
				.given()
				.log().all()
				.get(BASE_URL + URL_POSTFIX)
				.then()
				.log().all()
				.statusCode(HttpStatus.OK.value());
		int expected = 3;
		Assertions.assertEquals(expected, eventRepository.findAllEntity().size());
	}
}
