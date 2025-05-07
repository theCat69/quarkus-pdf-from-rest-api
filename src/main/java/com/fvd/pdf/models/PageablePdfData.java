package com.fvd.pdf.models;

import java.util.Collection;

public interface PageablePdfData<T> {
 Collection<T> getPdfPageData();
}
