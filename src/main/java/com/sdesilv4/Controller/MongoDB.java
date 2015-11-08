package com.sdesilv4.Controller;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sdesilv4.model.Action;
import com.sdesilv4.model.Indice;
import org.bson.Document;
import com.mongodb.MongoURI;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import static java.util.Arrays.asList;
import com.mongodb.MongoCredential;


/**
 * Created by Bizeray on 06/11/2015.
 */
public class MongoDB {
    public MongoDB(){

    }

    public String AddAction(Action act)
    {
        try
        {

            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("action");

            Document doc = new Document().append("name", act.getNom())
                    .append("codeISIN", act.getCodeISIN())
                    .append("prix", act.getPrix())
                    .append("date",act.getDate())
                    .append("volatility", act.getVolatility())
                    .append("volume",act.getVolume())
                    .append("cap_boursiere", act.getCapBoursiere())
                    .append("PER", act.getPER());

            coll.insertOne(doc);


        }
        catch (Exception e)
        { return e.getMessage();}

        return "action bien ajoute";

    }

    public String AddIndice(Indice ind)
    {
        try
        {
            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("indice");


            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date",ind.getDate())
                    .append("volume", ind.getVolume());
            coll.insertOne(doc);
        }
        catch (Exception e)
        { return e.getMessage();}

        return "indice bien ajoute";

    }

    public Action FindAction(String CodeISIN)
    {
        try
        {
            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("action");

            FindIterable<Document> g = coll.find(new Document("codeISIN", CodeISIN));
            String name = (String) g.first().get("name");
            String codeISIN = (String) g.first().get("codeISIN");
            Double prix = (Double) g.first().get("prix");
            Date date = (Date) g.first().get("date");
            Long volume = (Long) g.first().get("volume");
            Double volatility = (Double) g.first().get("volatility");
            Double cap_boursiere = (Double) g.first().get("cap_boursiere");
            Double PER = (Double) g.first().get("PER");

            Action act = new Action (name,codeISIN,prix,volume,date,"symbol",volatility,cap_boursiere,PER);    //symbol a compléter !!
            return act;
        }
        catch(Exception e) {
            return null;
        }
    }


    public Indice FindIndice(String CodeISIN)
    {
        try
        {
            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("indice");

            FindIterable<Document> g = coll.find(new Document("codeISIN", CodeISIN));
            String name = (String) g.first().get("name");
            String codeISIN = (String) g.first().get("codeISIN");
            Double prix = (Double) g.first().get("prix");
            Date date = (Date) g.first().get("date");
            Long volume = (Long) g.first().get("volume");
            Double volatility = (Double) g.first().get("volatility");

            Indice ind = new Indice(name,codeISIN,prix,volume,date , "symbol !! "); //symbol a compléter !!
            return ind;
        }
        catch(Exception e) {
            return null;
        }
    }

    public String UpdateAction(Action act) {
        try {
            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("action");

            Document doc = new Document().append("name", act.getNom())
                    .append("codeISIN", act.getCodeISIN())
                    .append("prix", act.getPrix())
                    .append("date", act.getDate())
                    .append("volatility", act.getVolatility())
                    .append("volume", act.getVolume())
                    .append("cap_boursiere", act.getCapBoursiere())
                    .append("PER", act.getPER());

            coll.updateOne(new Document("codeISIN", act.getCodeISIN()), doc);


        } catch (Exception e) {
            return e.getMessage();
        }

        return "action bien modifier";
    }

    public String UpdateIndice(Indice ind) {
        try {
            String uriString = "mongodb://groupeMif1:groupeMif1@ds051524.mongolab.com:51524/actif";
            MongoClientURI uri = new MongoClientURI(uriString);
            MongoClient client = new MongoClient((uri));
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            MongoCollection coll = db.getCollection("indice");

            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date",ind.getDate())
                    .append("volume", ind.getVolume());

            coll.updateOne(new Document("codeISIN", ind.getCodeISIN()), doc);


        } catch (Exception e) {
            return e.getMessage();
        }

        return "indice bien modifier";
    }
}
