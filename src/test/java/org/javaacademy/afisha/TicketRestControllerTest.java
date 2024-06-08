package org.javaacademy.afisha;

import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.model.dto.TicketDtoRq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class TicketRestControllerTest {
	public final String URL_POSTFIX = "/ticket";
	@Autowired
	private InitTempDataMethod initTempDataMethod;
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Test
	void buyTicketSuccess() {
		List<PlaceDto> placeDtoList = initTempDataMethod.createPlaces();
		placeDtoList.forEach(e -> initTempDataMethod.addNewPlace(e, "/place"));
		List<EventDtoRq> eventDtoRqList = initTempDataMethod.createEvents();
		eventDtoRqList.forEach(e -> initTempDataMethod.addNewEvent(e, "/event"));
		List<TicketDtoRq> ticketDtoRqList = initTempDataMethod.createTicketDtoRq();
		ticketDtoRqList.forEach(e -> initTempDataMethod.buyTicketSuccess(e, URL_POSTFIX));
	}

	@Test
	void buyTicketFailedNotAcceptableStatus() {
		List<PlaceDto> placeDtoList = initTempDataMethod.createPlaces();
		placeDtoList.forEach(e -> initTempDataMethod.addNewPlace(e, "/place"));
		List<EventDtoRq> eventDtoRqList = initTempDataMethod.createEvents();
		eventDtoRqList.forEach(e -> initTempDataMethod.addNewEvent(e, "/event"));

		TicketDtoRq ticketDtoRqCinema = new TicketDtoRq();
		ticketDtoRqCinema.setEventName("Кинотеатр");
		ticketDtoRqCinema.setEmail("test@mail.xyz");

		String sql = """
                update application.ticket
                set client_email = 'test@mail.xyz',
                is_selled = true
                """;
		jdbcTemplate.update(sql);

		initTempDataMethod.buyTicketFailed(ticketDtoRqCinema, URL_POSTFIX);
	}

}
