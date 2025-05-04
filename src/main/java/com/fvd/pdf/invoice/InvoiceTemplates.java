package com.fvd.pdf.invoice;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@CheckedTemplate(basePath = "invoice")
public class InvoiceTemplates {
    public static native TemplateInstance invoice(InvoiceData data);

}
