package br.com.cwi.currecyconversion.model;

import br.com.cwi.currecyconversion.dao.CurrentyCsvDao;
import br.com.cwi.currecyconversion.dao.CurrentyDao;
import br.com.cwi.currecyconversion.util.DateHandle;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Model with the data that the controller needs to do its operations.
 * It has access to the DAO by data.
 *
 * Created by Jonas Rodrigues on 07/08/2016.
 */
public class CurrentyCsvModel implements CurrentyModel {

    public static final String QUOTATION_PATTERN_DATE = "dd/MM/yyyy";
    public static final boolean RELOAD_DATA_QUOTAION = true;
    private CurrentyDao currentyCvsDao;
    private Map<String,Currenty> quotaionsMap;

    public CurrentyCsvModel(CurrentyDao currentyCvsDao){
        this.currentyCvsDao = currentyCvsDao;
    }

    /**
     * @return lazy of model - {@link CurrentyCsvDao}.
     */
    private CurrentyDao getDao() {
        if (currentyCvsDao == null) {
            this.currentyCvsDao = new CurrentyCsvDao();
        }

        return currentyCvsDao;
    }

    @Override
    public BigDecimal quoteByInitialsCurrency(String initialsCurrenty, String quotationDate) {
        Currenty quotation;
        Map<String, Currenty> quotationsMap =  getQuotaionsMap(!RELOAD_DATA_QUOTAION, quotationDate);

        if(quotationsMap.containsKey(initialsCurrenty)){
            quotation = quotationsMap.get(initialsCurrenty);
        } else{
            throw new IllegalArgumentException("Initials current not found!");
        }

        return quotation.getBuyRate();
    }

    private Map<String, Currenty> getQuotaionsMap(boolean isReloadData, String quotaionDate) {
        if (quotaionsMap == null || isReloadData == RELOAD_DATA_QUOTAION){
            LocalDate formatLocationDate = DateHandle.getInstance().convertFromStringToLocalDate(quotaionDate, QUOTATION_PATTERN_DATE);

            this.quotaionsMap = getDao().selectAll(formatLocationDate);
        }

        return quotaionsMap;
    }
}
