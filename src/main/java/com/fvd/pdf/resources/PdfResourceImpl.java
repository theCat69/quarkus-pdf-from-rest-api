package com.fvd.pdf.resources;

import com.fvd.pdf.data.InvoiceData;
import com.fvd.pdf.generator.InvoicePdfGenerator;
import com.fvd.pdf.modelsopenapi.PdfResource;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.translations.TranslationCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class PdfResourceImpl implements PdfResource {

  private final TranslationCache translationCache;
  private final InvoicePdfGenerator invoicePdfGenerator;

  @Override
  public Response createPdfFromInvoice(@NotNull Invoice data) {
    return Response.ok(invoicePdfGenerator.generatePdf(new InvoiceData(data,
        translationCache.getTranslationMap().get("FR")
      ))).build(); }

}
