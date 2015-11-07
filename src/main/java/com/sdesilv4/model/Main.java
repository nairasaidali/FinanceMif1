package com.sdesilv4.model;

import com.sdesilv4.Controller.ControllerGestionIndice;

import java.util.Date;

/**
 * Created by Nicolas on 03/11/15.
 */
public class Main {
    public static void main(String[] args)
    {

       Action a = new Action("ESILV", "FR24512154651", -5.5, 5122121, new Date(),"¨chi",5,41,1);
       Action a2 = new Action("ESILV", "FR24512154651", -5.5, 5122121, new Date(),"¨chi",5,41,1);

        Indice i = new Indice("Blague", "FR21213135",23,65+65,new Date(), "^chi");
////        MongoDB tst = new MongoDB();
////        String z = tst.AddAction(a);
////        String b = tst.AddIndice(i);
        i.addACtion(a,6);
        i.addACtion(a2,6);
        System.out.println(i.toString());

        ControllerGestionIndice CGi = new ControllerGestionIndice("FCHI");      //Symbole sans le ^ devant
    }
}
