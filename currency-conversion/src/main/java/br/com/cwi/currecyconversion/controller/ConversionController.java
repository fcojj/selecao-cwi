package br.com.cwi.currecyconversion.controller;

import br.com.cwi.currecyconversion.model.CurrentyModel;
import br.com.cwi.currecyconversion.util.DateHandle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Responsible for managing the currency
 * conversion activities.
 *
 * Created by Fco Jonas Rodrigues on 07/08/2016.
 */
public class ConversionController implements ConversionOperations{

    public static final String PATTERN_CSV_DATE = "dd/MM/yyyy";
    private CurrentyModel model;

    public ConversionController(CurrentyModel model){
        this.model = model;
    }

    private CurrentyModel getModel() {
        return model;
    }

    /**
     * Convert from currenty A to B.
     *
     * @param from - I own currenty.
     * @param to - I want currenty.
     * @param value - I own value.
     * @param quotation - date of quotation in format dd/MM/yyyy
     * @return value returned after exchange.
     */
    @Override
    public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) {
        verifyLessZeroValue(value);
        String validWeekDay = getWorkingDay(quotation);

        BigDecimal ownCurrentyInRealQuotation = getModel().quoteByInitialsCurrency(from, validWeekDay);
        BigDecimal wantCurrentyInRealQuotation = getModel().quoteByInitialsCurrency(to, validWeekDay);

        return converteCurrenty(ownCurrentyInRealQuotation, wantCurrentyInRealQuotation, value);
    }

    /**
     * Checks if the date is Saturday or Sunday,
     * if so sets the date for the previous working day closer.
     *
     * @param quotationDate - Data I want to quote.
     * @return Date of the week within working days.
     */
    private String getWorkingDay(String quotationDate) {
        LocalDate quotationLocalDate = DateHandle.getInstance().convertFromStringToLocalDate(quotationDate, PATTERN_CSV_DATE);

        if(quotationLocalDate.getDayOfWeek() == DayOfWeek.SATURDAY){
            quotationLocalDate = quotationLocalDate.minusDays(1);
        }else if(quotationLocalDate.getDayOfWeek() == DayOfWeek.SUNDAY){
            quotationLocalDate = quotationLocalDate.minusDays(2);
        }

        String quotaionStringDate = DateHandle.getInstance().convertFromLocalDateToString(quotationLocalDate,PATTERN_CSV_DATE);

        return quotaionStringDate;
    }

    /**
     * checks if the value is greater or desire to convert iguall to zero, otherwise throws exception
     *
     * @param value - value that I want to convert.
     */
    private void verifyLessZeroValue(Number value) {
        if(value.doubleValue() < 0){
            throw new NumberFormatException("Value small that zero!");
        }
    }


    /**
     *
     * Realize the exchange using the corresponding values in real.
     *
     * @param fromCurrentyInRealQuotation - I own currenty in Real.
     * @param toCurrentyInRealQuotation - I want currentyin Real.
     * @param ownValue - I own value.
     * @return value returned after exchange.
     */
    private BigDecimal converteCurrenty(BigDecimal fromCurrentyInRealQuotation, BigDecimal toCurrentyInRealQuotation, Number ownValue) {
        BigDecimal totalFromInRealValue = fromCurrentyInRealQuotation.multiply(BigDecimal.valueOf(ownValue.doubleValue()));

        return (totalFromInRealValue).divide(toCurrentyInRealQuotation, 2, RoundingMode.DOWN);
    }
}
