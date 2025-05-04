package com.fvd.pdf.invoice;

import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.modelsopenapi.beans.InvoiceItem;
import io.quarkus.qute.TemplateExtension;

import java.math.BigDecimal;

@TemplateExtension
public class InvoiceExtensions {

  public static BigDecimal amount(InvoiceItem invoiceItem) {
    return BigDecimal.valueOf(invoiceItem.getUnitPrice()).multiply(BigDecimal.valueOf(invoiceItem.getQuantity()));
  }

  public static BigDecimal total(Invoice invoice) {
    return invoice.getInvoiceItems().stream().map(InvoiceExtensions::amount)
      .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }
}
