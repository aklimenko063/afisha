package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
@Tag(name = "Place controller", description = "Методы по работе со площадками проведений мероприятий")
public class PlaceRestController {
    private final PlaceService placeService;

    @PostMapping
    @Operation(summary = "Создание площадки проведения мероприятия")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400")})
    public ResponseEntity<?> addNewPlace(@RequestBody PlaceDto placeDto) {
        return status(CREATED).body(placeService.addNewPlace(placeDto));
    }

    @GetMapping
    @Operation(summary = "Получение всех площадок проведения мероприятия")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class)))
    public ResponseEntity<?> findAllPlaces() {
        return status(OK).body(placeService.findAllPlaces());
    }
}
