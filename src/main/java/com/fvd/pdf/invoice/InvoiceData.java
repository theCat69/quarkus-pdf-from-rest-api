package com.fvd.pdf.invoice;

import com.fvd.pdf.dev.utils.InvoiceGenerator;
import com.fvd.pdf.models.PageablePdfData;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import io.quarkus.qute.TemplateData;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
@TemplateData
public class InvoiceData implements PageablePdfData<Invoice>, InvoiceGenerator {
  private final Invoice invoice;
  private final Map<String, String> translations;
  private final List<Invoice> pdfPageData;

  public InvoiceData(Invoice invoice, Map<String, String> translations) {
    this.invoice = invoice;
    this.translations = translations;
    this.pdfPageData = initPageData(invoice);
  }

  private List<Invoice> initPageData(Invoice invoice) {
    if(invoice.getInvoiceItems().size() > 5) {
      final AtomicInteger counter = new AtomicInteger(0);
      return invoice.getInvoiceItems().stream()
        .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/5))
        .values().stream().map(invoiceItems -> generateInvoice(invoice.getInvoiceNumber(), LocalDate.now(),
          invoice.getCustomerName(), invoice.getCustomerAddress(), invoiceItems)).toList();
    }
    return List.of(invoice);
  }

}
