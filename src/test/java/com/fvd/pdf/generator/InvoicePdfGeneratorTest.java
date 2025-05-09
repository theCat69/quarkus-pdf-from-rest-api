package com.fvd.pdf.generator;

import com.fvd.pdf.InvoiceTester;
import com.fvd.pdf.invoice.InvoiceData;
import com.fvd.pdf.invoice.InvoicePdfGenerator;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.translations.TranslationCache;
import io.quarkus.test.junit.QuarkusTest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;

//TODO assert pdf content
@QuarkusTest
@RequiredArgsConstructor
class InvoicePdfGeneratorTest extends InvoiceTester {
  final InvoicePdfGenerator invoicePdfGenerator;
  final TranslationCache translationCache;

  @BeforeEach
  @SneakyThrows
  void setup() {
    Path testPDfFolder = Path.of("test", "pdf");
    Files.createDirectories(testPDfFolder);
    FileUtils.cleanDirectory(testPDfFolder.toFile());
  }

  @Test
  @SneakyThrows
  void generatedPdfFromHtml_test() {
    //given
    Invoice invoice = generateInvoice("INV-2025-0001", LocalDate.now(),
      "John Doe", "123 Main St, Anytown, CA 12345",
      Arrays.asList(
        generateInvoiceItem("Product A", 2, 25.99),
        generateInvoiceItem("Product B", 1, 59.99),
        generateInvoiceItem("Product C", 3, 12.50)
      )
    );
    //when
    byte[] pdfBytes = invoicePdfGenerator.generatePdf(new InvoiceData(invoice,
      translationCache.getTranslationMap().get("FR")));
    //then
    Files.write(Path.of("test", "pdf", "test.pdf"), pdfBytes);
  }
}