package com.eshop.backendpaymentapi.app.api.payment.contract;

import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.create.CreatePaymentCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payments Commands")
@RequestMapping(value = "/payments")
public interface PaymentCommandAPIContract {

  @Operation(summary = "Create a new payment")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created successfully"),
    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<?>createPayment(@RequestBody final CreatePaymentCommand command);

  @Operation(summary = "Delete a payment by it's identifier")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Payment delete successfully"),
    @ApiResponse(responseCode = "404", description = "Payment was not found"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  void getPaymentById(@PathVariable(name = "id") String id);
}
