package com.fvd.pdf.generator;

import io.quarkus.qute.TemplateInstance;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.function.Function;

public interface PdfGenerator<T> {
  Function<T, TemplateInstance> getTemplate();

  default byte[] generatePdf(T data) {
    try {
      // Load the HTML template
      TemplateInstance templateInstance = getTemplate().apply(data);

      // Replace placeholders with actual data
      String processedHtml = templateInstance.render();

      // Convert HTML to PDF
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(processedHtml);
      renderer.layout();

      // handle multipage case
      int pageNumber = renderer.getRootBox().getLayer().getPages().size();
      if(pageNumber > 1) {
        return handleMultiPage(pageNumber, data, renderer, outputStream);
      }

      renderer.createPDF(outputStream);
      return outputStream.toByteArray();
    } catch (Exception e) {
      throw new RuntimeException("Error generating PDF", e);
    }
  }

  default byte[] handleMultiPage(int pageNumber, T data, ITextRenderer renderer, ByteArrayOutputStream outputStream) {
    renderer.createPDF(outputStream);
    return outputStream.toByteArray();
  }

}
