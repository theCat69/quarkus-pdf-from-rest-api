package com.fvd.pdf;

import com.fvd.pdf.modelsopenapi.beans.Invoice;
import com.fvd.pdf.modelsopenapi.beans.InvoiceItem;

import java.time.LocalDate;
import java.util.List;

public abstract class InvoiceTester {

  protected Invoice generateInvoice(String number, LocalDate date, String customerName, String customerAddress,
                                  List<InvoiceItem> invoiceItems) {
    var invoice = new Invoice();
    invoice.setInvoiceNumber(number);
    invoice.setDate(date.toString());
    invoice.setCustomerName(customerName);
    invoice.setCustomerAddress(customerAddress);
    invoice.setInvoiceItems(invoiceItems);
    return invoice;
  }

  protected InvoiceItem generateInvoiceItem(String description, int quantity, double price) {
    var invoiceItem = new InvoiceItem();
    invoiceItem.setDescription(description);
    invoiceItem.setQuantity(quantity);
    invoiceItem.setUnitPrice(price);
    return invoiceItem;
  }
}
