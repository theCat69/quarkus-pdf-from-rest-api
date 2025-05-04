package com.fvd.pdf.dev.resources;

import com.fvd.pdf.invoice.InvoiceData;
import com.fvd.pdf.dev.utils.InvoiceGenerator;
import com.fvd.pdf.invoice.InvoicePdfGenerator;
import com.fvd.pdf.invoice.InvoiceResourceImpl;
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

import java.time.LocalDate;
import java.util.Arrays;

@Path("/dev")
@ApplicationScoped
@IfBuildProfile("dev")
@RequiredArgsConstructor
public class DevResource implements InvoiceGenerator {

  private final TranslationCache translationCache;
  private final InvoicePdfGenerator invoicePdfGenerator;

  @GET
  @Path("/html/invoice")
  @Produces(MediaType.TEXT_HTML)
  public String invoiceTemplate() {
    Invoice invoice = generateInvoice("INV-2025-0001", LocalDate.now(),
      "John Doe", "123 Main St, Anytown, CA 12345",
      Arrays.asList(
        generateInvoiceItem("Product A", 2, 25.99),
        generateInvoiceItem("Product B", 1, 59.99),
        generateInvoiceItem("Product C", 3, 12.50)
      )
    );
    return InvoiceTemplates.invoice(new InvoiceData(invoice, translationCache.getTranslationMap().get("FR"))).render();
  }

  @GET
  @Path("/pdf/invoice")
  @Produces("application/pdf")
  public byte[] invoicePdf() {
    Invoice invoice = generateInvoice("INV-2025-0001", LocalDate.now(),
      "John Doe", "123 Main St, Anytown, CA 12345",
      Arrays.asList(
        generateInvoiceItem("Product A", 2, 25.99),
        generateInvoiceItem("Product B", 1, 59.99),
        generateInvoiceItem("Product C", 3, 12.50)
      )
    );
    return invoicePdfGenerator.generatePdf(new InvoiceData(invoice, translationCache.getTranslationMap().get("FR")));
  }
}
