package br.com.cwi.currecyconversion.dao;

import br.com.cwi.currecyconversion.model.Currenty;
import br.com.cwi.currecyconversion.util.DateHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsável por minerar os dados da fonte, gerando o model.
 * Using as one source .csv file extension.
 *
 * Created by Jonas Rodrigues on 07/08/2016.
 */
public class CurrentyCsvDao implements CurrentyDao{
    private static final String QUOTATION_FILE_CVS_URL = "http://www4.bcb.gov.br/Download/fechamento/{0}.csv";
    private static final String SEARCH_CSV_PATTERN_DATE = "yyyy/MM/dd";
    public static final int DATE_QUOTATION_INDEX = 0;
    private static final int ID_INDEX = 1;
    private static final int INITIALS_CURRENTY_INDEX = 2;
    private static final int TYPE_INDEX = 3;
    private static final int BUY_RATE_INDEX = 4;
    private static final int SALE_RATE_INDEX = 5;
    private static final int BUY_PARITY_INDEX = 6;
    private static final int SALE_PARITY_INDEX = 7;
    public static final String QUOTATION_PATTERN_DATE = "dd/MM/yyyy";

    @Override
    public Map<String, Currenty> selectAll(LocalDate date) {
        Map<String, Currenty> quotaionsMap = new HashMap<>();
        String url = buildUrl(date);

        try {
            InputStream is = new URL(url).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Stream<String> stream = reader.lines();
            List<List<String>> lineList = stream.map(line -> Arrays.asList(line.split(";"))).collect(Collectors.toList());
            lineList.forEach(line->{ quotaionsMap.put(line.get(3), extractCurrenty(line)); });
        } catch (IOException ex){
            throw new IllegalArgumentException("Search not available.");
        }

        return quotaionsMap;
    }

    private String buildUrl(LocalDate date) {
        String dateForSearchCsvWithBars  = DateHandle.getInstance().convertFromLocalDateToString(date, SEARCH_CSV_PATTERN_DATE);
        Object[] dateForSearchCsvWithoutBars = {dateForSearchCsvWithBars.replace("/","")};
        MessageFormat urlSearchCsvWithoutDate = new MessageFormat(QUOTATION_FILE_CVS_URL);
        String urlSearchCsvWithDate = urlSearchCsvWithoutDate.format(dateForSearchCsvWithoutBars);

        return urlSearchCsvWithDate;
    }

    private Currenty extractCurrenty(List<String> quotationsList){
        LocalDate data = DateHandle.getInstance().formateLocalDate(quotationsList.get(DATE_QUOTATION_INDEX), QUOTATION_PATTERN_DATE);
        String id = quotationsList.get(ID_INDEX);
        String initialsCurrenty = quotationsList.get(INITIALS_CURRENTY_INDEX);
        String type = quotationsList.get(TYPE_INDEX);
        BigDecimal buyRate = new BigDecimal(quotationsList.get(BUY_RATE_INDEX).replace(",","."));
        BigDecimal saleRate = new BigDecimal(quotationsList.get(SALE_RATE_INDEX).replace(",","."));
        BigDecimal buyParity = new BigDecimal(quotationsList.get(BUY_PARITY_INDEX).replace(",","."));
        BigDecimal saleParity = new BigDecimal(quotationsList.get(SALE_PARITY_INDEX).replace(",","."));

        return new Currenty(data, id, initialsCurrenty, type, buyRate,saleRate, buyParity, saleParity);
    }
}
