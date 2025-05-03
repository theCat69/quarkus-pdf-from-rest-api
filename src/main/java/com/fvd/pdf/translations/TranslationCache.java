package com.fvd.pdf.translations;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.util.Map;

@Getter
@ApplicationScoped
public class TranslationCache {

  private final Map<String, Map<String, String>> translationMap = Map.of(
    "FR", Map.of(
      "invoice.title", "Facture",
          "name", "nom"
    )
  );
}
