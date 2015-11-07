package com.sdesilv4.Controller;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Nicolas on 07/11/15.
 */
public class ControllerGestionIndiceTest {
    ControllerGestionIndice cgi = new ControllerGestionIndice("FCHI");
    List<String> listAction;

    @Test
    public void testGetListOfSymbols() throws Exception
    {
        assertFalse(cgi.getListOfSymbols().size()!=40);
    }
}