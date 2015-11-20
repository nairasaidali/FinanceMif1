package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;
import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by Dilan on 20/11/2015.
 */
public class MongoDBClientTest extends TestCase {

    public void testFindAction() throws Exception {

        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        Action act = tst.FindAction("CREDIT AGRICOLE");
        //assertEquals(6361456, act.getVolume());   //Retourne bien une erreur et indique la bonne valeur du volume
        assertEquals(6361466, act.getVolume());
    }

    public void testAddAction() throws Exception {
        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        Action act = new Action("NIKE","NIKE",131.68,6525990, new Date(),"^FCHI",0, 1122100000,0);
        tst.AddAction(act); //Ajout de l'Action Nike dans la base de données

        Action actNike = tst.FindAction("NIKE");    //Test si l'Action a été ajoutée
        assertEquals(6525990, actNike.getVolume()); //Vérifie si on a bien la meme valeur pour le volume
    }
}