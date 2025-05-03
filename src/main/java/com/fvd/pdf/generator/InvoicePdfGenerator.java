package com.fvd.pdf.generator;

import com.fvd.pdf.Templates;
import com.fvd.pdf.data.InvoiceData;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.function.Function;

@ApplicationScoped
@Getter
@RequiredArgsConstructor
public class InvoicePdfGenerator implements PdfGenerator<InvoiceData> {

  private final Function<InvoiceData, TemplateInstance> template = Templates::invoice;

  //Can override here to manipulate data
  @Override
  public byte[] handleMultiPage(int pageNumber, InvoiceData data, ITextRenderer renderer, ByteArrayOutputStream outputStream) {
    //here we can do something with the data then render multiple pdf that we merge as one each including part of some
    // Collection data that should be displayed on multiple pages.
    return PdfGenerator.super.handleMultiPage(pageNumber, data, renderer, outputStream);
  }
}
