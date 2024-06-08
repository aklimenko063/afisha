package org.javaacademy.afisha;

import io.restassured.RestAssured;
import org.javaacademy.afisha.model.dto.PlaceDto;
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
class ReportRestControllerTest {
	private final String URL_POSTFIX = "/report";
	@Autowired
	private InitTempDataMethod initTempDataMethod;

	@Test
	void getReportSuccess() {
		List<PlaceDto> places = initTempDataMethod.createPlaces();
		places.forEach(placeDto -> initTempDataMethod.addNewPlace(placeDto, "/place"));
		RestAssured
				.given()
				.log().all()
				.get(BASE_URL + URL_POSTFIX)
				.then()
				.log().all()
				.statusCode(HttpStatus.OK.value());
	}
}
