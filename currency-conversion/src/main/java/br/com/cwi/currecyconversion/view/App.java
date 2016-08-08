package br.com.cwi.currecyconversion.view;

import br.com.cwi.currecyconversion.controller.ConversionControllerBuilder;
import br.com.cwi.currecyconversion.controller.ConversionOperations;

import java.util.IllegalFormatException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class main, instance the application controller.
 *
 * @author Fco Jonas Rodrigues
 */
public class App{
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args){

        ConversionControllerBuilder builderController = new ConversionControllerBuilder();
        ConversionOperations controller = builderController.getBuid();

        try {
            System.out.println(controller.currencyQuotation("USD", "EUR", 100.0, "05/08/2016"));
        }catch(NumberFormatException e1){
            LOGGER.log(Level.WARNING, "Exception occur: ", e1);
        }catch(IllegalArgumentException e2){
            LOGGER.log(Level.WARNING, "Exception occur: ", e2);
        }

    }
}
