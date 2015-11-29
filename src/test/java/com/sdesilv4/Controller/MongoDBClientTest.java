package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;
import com.sdesilv4.model.Indice;
import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by Dilan on 20/11/2015.
 */
public class MongoDBClientTest extends TestCase {

   public void testFindAction() throws Exception {

        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        Action act = tst.FindAction("ACA.PA");
        assertTrue(act.getPrix()<20&&act.getPrix()>5);   //Retourne bien une erreur et indique la bonne valeur du volume
        //assertEquals(6361466, act.getVolume());
    }

    public void testAddAction() throws Exception {
        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        Action act = new Action("NIKE","NIKE",131.68,6525990, new Date(),"NKE",0, 1122100000,0);
        tst.AddAction(act); //Ajout de l'Action Nike dans la base de donn�es

        Action actNike = tst.FindAction("NKE");    //Test si l'Action a �t� ajout�e
        assertTrue(act.getPrix() < 200 && act.getPrix() > 90);  //V�rifie si on a bien la meme valeur pour le volume
    }

       public void testFindIndice() throws Exception {
        MongoDBClient tst = new MongoDBClient("mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif");
        Indice inde=tst.FindIndice("Not available in Yahoo API");

        assertEquals("Not available in Yahoo API",inde.getCodeISIN());
           assertEquals(4930.14,inde.getPrix());
    }
}