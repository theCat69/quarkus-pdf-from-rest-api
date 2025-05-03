package com.fvd.pdf;

import com.fvd.pdf.modelsopenapi.beans.Invoice;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate
public class Templates {
  public static native TemplateInstance invoice(Invoice invoice);
}
