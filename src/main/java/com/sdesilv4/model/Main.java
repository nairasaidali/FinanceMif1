package com.sdesilv4.model;

import com.sdesilv4.Controller.ControllerGestionIndice;
import com.sdesilv4.Controller.MongoDBClient;

/**
 * Created by Nicolas on 03/11/15.
 */
public class Main {
    public static void main(String[] args){
        ControllerGestionIndice CGi = new ControllerGestionIndice("^FCHI");      //Symbole sans le ^ devant
        System.out.println(CGi.GetIndex().toString());

        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        tst.AddIndice(CGi.GetIndex());
        tst.CloseClientConnection();
        // Commande Bash        mongo ds051524.mongolab.com:51524/actif -u groupeMif1 -p groupeMif1
    }
}