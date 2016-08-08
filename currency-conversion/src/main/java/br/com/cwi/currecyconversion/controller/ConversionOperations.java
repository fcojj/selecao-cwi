package br.com.cwi.currecyconversion.controller;

import java.math.BigDecimal;

/**
 *
 * Interface with all conversions operations.
 * All who want to convert implentar need this interface.
 *
 * Created by Fco Jonas Rodrigues on 07/08/2016.
 */
public interface ConversionOperations {
    BigDecimal currencyQuotation(String from, String to, Number value, String quotation);
}
