package com.fvd.pdf.dev.resources;

import com.fvd.pdf.dev.utils.InvoiceGenerator;
import com.fvd.pdf.invoice.InvoiceData;
import com.fvd.pdf.invoice.InvoicePdfGenerator;
import com.fvd.pdf.invoice.InvoiceTemplates;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.translations.TranslationCache;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/dev")
@ApplicationScoped
@IfBuildProfile("dev")
@RequiredArgsConstructor
public class DevResource implements InvoiceGenerator {

  private final TranslationCache translationCache;
  private final InvoicePdfGenerator invoicePdfGenerator;

  private Invoice exampleInvoice() {
    return generateInvoice("INV-2025-0001", LocalDate.now(),
      "John Doe", "123 Main St, Anytown, CA 12345",
      Arrays.asList(
        generateInvoiceItem("Product A", 2, 25.99),
        generateInvoiceItem("Product B", 1, 59.99),
        generateInvoiceItem("Product C", 3, 12.50),
        generateInvoiceItem("Product C", 3, 12.50),
        generateInvoiceItem("Product C", 3, 12.50),
        generateInvoiceItem("Product C", 3, 12.50),
        generateInvoiceItem("Product C", 3, 12.50)
      )
    );
  }

  @GET
  @Path("/html/invoice")
  @Produces(MediaType.TEXT_HTML)
  public String invoiceTemplate() {
    Invoice invoice = exampleInvoice();
    return InvoiceTemplates.invoice(new InvoiceData(invoice, translationCache.getTranslationMap().get("FR"))).render();
  }

  @GET
  @Path("/pdf/invoice")
  @Produces("application/pdf")
  public byte[] invoicePdf() {
    Invoice invoice = exampleInvoice();
    return invoicePdfGenerator.generatePdf(new InvoiceData(invoice, translationCache.getTranslationMap().get("FR")));
  }

  @GET
  @Path("/images/base64")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, String> getImageFileAsBase64() {
    return FileUtils.listFiles(java.nio.file.Path.of("src/main/resources/base-images").toFile(), TrueFileFilter.INSTANCE,
        TrueFileFilter.INSTANCE)
      .stream()
      .collect(Collectors.toMap(
        File::getName,
        file -> {
          try {
            return Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      ));
  }
}
