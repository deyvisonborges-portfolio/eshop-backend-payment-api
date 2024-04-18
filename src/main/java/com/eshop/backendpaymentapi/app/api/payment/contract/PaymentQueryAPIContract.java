package com.eshop.backendpaymentapi.app.api.payment.contract;

import com.eshop.backendpaymentapi.lib.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Payments Queries")
@RequestMapping("/payments")
public interface PaymentQueryAPIContract {

  @Operation(summary = "List all payments paginated")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Listed successfully"),
    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  @GetMapping
  Pagination<?> listPayments(
    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
    @RequestParam(name = "sort", required = false, defaultValue = "status") final String sort,
    @RequestParam(name = "direction", required = false, defaultValue = "ascendant") final String direction
  );
}
