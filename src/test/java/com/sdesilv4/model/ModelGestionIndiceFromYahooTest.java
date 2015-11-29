package com.sdesilv4.model;

import com.sdesilv4.model.ModelGestionIndiceFromYahoo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Nicolas on 07/11/15.
 */
public class ModelGestionIndiceFromYahooTest {
    ModelGestionIndiceFromYahoo cgi = new ModelGestionIndiceFromYahoo("^FCHI");

    @Test
    public void testGetListOfSymbols() throws Exception
    {
        assertFalse(cgi.getListOfSymbols().size()!=40);
        assertTrue(cgi.getListOfSymbols().contains("FP.PA"));
        assertTrue(cgi.getListOfSymbols().contains("SAN.PA"));
        assertTrue(cgi.getListOfSymbols().contains("BNP.PA"));
    }

    @Test
    public void getListOfActionObjects() throws Exception
    {
        assertTrue(cgi.GetIndex().GetCollectionAction().size()==40);
        assertTrue(cgi.GetIndex().GetCollectionAction().contains(new Action("GLE.PA", "GLE.PA", 45, 54, new Date(), "GLE.PA", 54, 54, 54)));
        assertTrue(cgi.GetIndex().GetCollectionAction().contains(new Action("GLE.PA", "GLE.PA", 45, 54, new Date(), "SAN.PA", 54, 54, 54)));
        assertTrue(cgi.GetIndex().GetCollectionAction().contains(new Action("GLE.PA", "GLE.PA", 45, 54, new Date(), "BNP.PA", 54, 54, 54)));



    }


}