package com.fvd.pdf.invoice;

import com.fvd.pdf.modelsopenapi.InvoiceResource;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.translations.TranslationCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class InvoiceResourceImpl implements InvoiceResource {

  private final TranslationCache translationCache;
  private final InvoicePdfGenerator invoicePdfGenerator;

  @Override
  public Response createPdfFromInvoice(@NotNull Invoice invoice) {
    return Response.ok(invoicePdfGenerator.generatePdf(new InvoiceData(invoice,
        translationCache.getTranslationMap().get("FR")
      ))).build();
  }

}
