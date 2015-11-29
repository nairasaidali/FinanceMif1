package com.sdesilv4.model;

import junit.framework.TestCase;

import java.awt.geom.Arc2D;

/**
 * Created by Bilale on 29/11/2015.
 */
public class ModelGestionActionTest extends TestCase {

    public void testMarketCapt() throws Exception {
        ModelGestionAction modelMGA= new ModelGestionAction("AIR.PA");
       double MKC= modelMGA.MarketCapt("AIR.PA");
        assertEquals(MKC,5.089E10);// Verifie bien que la valeur attendu est 5.089E10
    }
}