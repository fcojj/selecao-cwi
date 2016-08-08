package br.com.cwi.currecyconversion.controller;

import br.com.cwi.currecyconversion.dao.CurrentyCsvDao;
import br.com.cwi.currecyconversion.dao.CurrentyDao;
import br.com.cwi.currecyconversion.model.CurrentyCsvModel;
import br.com.cwi.currecyconversion.model.CurrentyModel;

/**
 * Abstracts the complexity instantiation of the controller,
 * can be substituted by injection Spring dependencies.
 *
 * Created by Fco Jonas Rodrigues on 08/08/2016.
 */
public class ConversionControllerBuilder {

    private ConversionOperations converterCurrety;

    public ConversionOperations getBuid(){
        if(converterCurrety == null) {
            CurrentyDao dao = new CurrentyCsvDao();
            CurrentyModel model = new CurrentyCsvModel(dao);
            converterCurrety = new ConversionController(model);
        }

        return converterCurrety;
    }
}
