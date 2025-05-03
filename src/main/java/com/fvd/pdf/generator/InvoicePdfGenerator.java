package com.fvd.pdf.generator;

import com.fvd.pdf.Templates;
import com.fvd.pdf.modelsopenapi.beans.Invoice;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@ApplicationScoped
@Getter
@RequiredArgsConstructor
public class InvoicePdfGenerator implements PdfGenerator<Invoice> {

  private final Function<Invoice, TemplateInstance> template = Templates::invoice;

}
