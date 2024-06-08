package org.javaacademy.afisha;

import io.restassured.RestAssured;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.repository.PlaceRepository;
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
class PlaceRestControllerTest {
	private final String URL_POSTFIX = "/place";
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private InitTempDataMethod initTempDataMethod;

    @Test
	void addNewPlaceSuccess() {
		List<PlaceDto> places = initTempDataMethod.createPlaces();
		places.forEach(placeDto -> initTempDataMethod.addNewPlace(placeDto, URL_POSTFIX));
	}

	@Test
	void findAllPlacesSuccess() {
		List<PlaceDto> places = initTempDataMethod.createPlaces();
		places.forEach(placeDto -> initTempDataMethod.addNewPlace(placeDto, URL_POSTFIX));
		RestAssured
				.given()
				.log().all()
				.get(BASE_URL + URL_POSTFIX)
				.then()
				.log().all()
				.statusCode(HttpStatus.OK.value());
		int expected = 3;
		Assertions.assertEquals(expected, placeRepository.findAllEntity().size());
	}
}
