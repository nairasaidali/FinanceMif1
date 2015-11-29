package com.sdesilv4.model;

import com.sdesilv4.model.ModelGestionIndiceFromYahoo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nicolas on 07/11/15.
 */
public class ModelGestionIndiceFromYahooTest {
    ModelGestionIndiceFromYahoo cgi = new ModelGestionIndiceFromYahoo("FCHI");

    @Test
    public void testGetListOfSymbols() throws Exception
    {
        assertFalse(cgi.getListOfSymbols().size()!=40);
    }
}