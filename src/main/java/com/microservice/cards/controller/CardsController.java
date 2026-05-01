package com.microservice.cards.controller;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.dto.ErrorResponseDto;
import com.microservice.cards.dto.ResponseDto;
import com.microservice.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.microservice.cards.constants.CardsConstants.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(
        name = "CRUD REST APIs for Cards MS",
        description = "CRUD REST APIs in Cards MS to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "${api.version}/cards", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor // this will generate constructor for us for ILoansService
@Validated // for validations
public class CardsController {

    private ICardsService iCardsService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )}
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                  @Pattern(regexp="(^$|\\d{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )}
    )
    @GetMapping(value = "/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                     @Pattern(regexp="(^$|\\d{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )}
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )}
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_DELETE));
        }
    }
}
