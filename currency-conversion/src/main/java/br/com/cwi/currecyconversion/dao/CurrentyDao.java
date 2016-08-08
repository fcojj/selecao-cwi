package br.com.cwi.currecyconversion.dao;

import br.com.cwi.currecyconversion.model.Currenty;

import java.time.LocalDate;
import java.util.Map;

/**
 * Interface that abstracts how data is being accessed.
 * All the DAO implementations to access the currency information must implement this interface.
 *
 * Created by Fco Jonas Rodrigues on 07/08/2016.
 */
public interface CurrentyDao {
    Map<String,Currenty> selectAll(LocalDate date);
}
