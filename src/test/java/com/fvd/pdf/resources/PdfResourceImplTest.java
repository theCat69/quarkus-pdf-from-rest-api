package com.fvd.pdf.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fvd.pdf.InvoiceTester;
import io.quarkus.test.junit.QuarkusTest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

@QuarkusTest
@RequiredArgsConstructor
class PdfResourceImplTest extends InvoiceTester {

  @Test
  @SneakyThrows
  void testHelloEndpoint() {
    given()
      .header("Content-type", "application/json")
      .and()
      .body(
        generateInvoice(
          "INV-2025-0001", LocalDate.now(),
          "John Doe", "123 Main St, Anytown, CA 12345",
          Arrays.asList(
            generateInvoiceItem("Product A", 2, 25.99),
            generateInvoiceItem("Product B", 1, 59.99),
            generateInvoiceItem("Product C", 3, 12.50)
          )
        )
      )
      .when().post("/pdf/invoice")
      .then()
      .statusCode(200);
  }

}