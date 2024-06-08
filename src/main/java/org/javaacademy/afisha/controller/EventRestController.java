package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.EventDtoRq;
import org.javaacademy.afisha.model.dto.EventDtoRs;
import org.javaacademy.afisha.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
@Tag(name = "Event controller", description = "Методы по работе с мероприятиями")
public class EventRestController {
    private final EventService eventService;

    @PostMapping
    @Operation(summary = "Создание мероприятия")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDtoRs.class))),
            @ApiResponse(responseCode = "400")})
    public ResponseEntity<?> addNewEvent(@RequestBody EventDtoRq eventDtoRq) {
        return status(CREATED).body(eventService.addNewEvent(eventDtoRq));
    }

    @GetMapping
    @Operation(summary = "Получение всех мероприятия")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class)))
    public ResponseEntity<?> findAllEvents() {
        return status(OK).body(eventService.findAllEvents());
    }
}
