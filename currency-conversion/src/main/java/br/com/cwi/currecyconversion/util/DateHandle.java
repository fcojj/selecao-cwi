package br.com.cwi.currecyconversion.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Singleton for manipulation Date.
 * Created by Jonas Rodrigues on 07/08/2016.
 */
public class DateHandle {

    private static DateHandle dateHandle;

    private DateHandle() {
    }

    public static DateHandle getInstance(){
        if(dateHandle == null){
            initializeInstance();
        }

        return dateHandle;
    }

    /**
     * @param pattern - String is represent pattern da date receiver. Ex.: dd/MM/yyyy.
     * @param dateInString - String is represent a date. Ex.: 12/12/1988.
     * @return Return date in format of String in pattern defined.
     */
    public LocalDate convertFromStringToLocalDate(String dateInString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate fromStringToLocalDate = LocalDate.parse(dateInString,formatter);

        return fromStringToLocalDate;
    }

    public String convertFromLocalDateToString(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return  formatter.format(date);
    }

    public LocalDate formateLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate fromStringToLocalDate = LocalDate.parse(date,formatter);

        return fromStringToLocalDate;
    }


    private static synchronized void initializeInstance(){
        if (dateHandle == null){
            dateHandle = new DateHandle();
        }
    }


}