package br.com.cwi.currecyconversion.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Responsible for managing the currency
 * conversion activities.
 *
 * Created by Fco Jonas Rodrigues on 07/08/2016.
 */
public interface CurrentyModel{

    BigDecimal quoteByInitialsCurrency(String currentyType, String quotationDate);
}
