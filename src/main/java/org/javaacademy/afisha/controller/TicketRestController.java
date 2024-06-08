package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.TicketDtoRq;
import org.javaacademy.afisha.model.dto.TicketDtoRs;
import org.javaacademy.afisha.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
@Tag(name = "Ticket controller", description = "Методы по работе с билетами")
public class TicketRestController {
    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Метод покупки билета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDtoRs.class))),
            @ApiResponse(responseCode = "406"),
            @ApiResponse(responseCode = "400")})
    public ResponseEntity<?> buyTicket(@RequestBody TicketDtoRq ticketDtoRq) {
        return status(ACCEPTED).body(ticketService.buyTicket(ticketDtoRq));
    }

}
