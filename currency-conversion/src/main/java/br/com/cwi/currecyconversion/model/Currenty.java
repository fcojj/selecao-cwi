package br.com.cwi.currecyconversion.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * POJO that represents the data from one currency.
 *
 * Created by Fco Jonas Rodrigues on 07/08/2016.
 */
public class Currenty {
    private LocalDate quotationDate;
    private String initialsCurrenty;
    private String id;
    private String type;
    private BigDecimal buyRate;
    private BigDecimal seleRate;
    private BigDecimal buyParity;
    private BigDecimal saleParity;


    public Currenty(LocalDate quotationDate, String id, String initialsCurrenty, String type,
                    BigDecimal buyRate, BigDecimal seleRate, BigDecimal buyParity, BigDecimal saleParity) {
        this.quotationDate = quotationDate;
        this.id = id;
        this.initialsCurrenty = initialsCurrenty;
        this.type = type;
        this.buyRate = buyRate;
        this.seleRate = seleRate;
        this.buyParity = buyParity;
        this.saleParity = saleParity;
    }

    public LocalDate getQuotationDate() {
        return quotationDate;
    }

    public String getId() { return id; }

    public String getInitialsCurrenty() {
        return initialsCurrenty;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public BigDecimal getSeleRate() {
        return seleRate;
    }

    public BigDecimal getBuyParity() {
        return buyParity;
    }

    public BigDecimal getSaleParity() {
        return saleParity;
    }
}
