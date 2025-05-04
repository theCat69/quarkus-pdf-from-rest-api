package com.fvd.pdf.invoice;

import com.fvd.pdf.modelsopenapi.beans.Invoice;
import io.quarkus.qute.TemplateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@TemplateData
public class InvoiceData {
  private final Invoice invoice;
  private final Map<String, String> translations;
}
