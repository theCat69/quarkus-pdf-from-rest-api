package com.fvd.pdf.template.extensions;

import io.quarkus.qute.TemplateExtension;
import org.apache.commons.lang3.StringUtils;

@TemplateExtension(namespace = "str")
public class StringExtensions {

  static String capitalize(String val) {
    return StringUtils.capitalize(val);
  }

}
