package com.sdesilv4.Controller;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sdesilv4.model.Action;
import com.sdesilv4.model.Indice;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import static java.util.Arrays.asList;

import javax.print.Doc;


/**
 * Created by Bizeray on 06/11/2015.
 */
public class MongoDBClient
{
    private String uriString;
    private MongoClient connectionClient;
    private MongoClientURI uri;
    private MongoDatabase db;

    public MongoDBClient(String _uriString)
    {
        this.uriString = _uriString;
        this.uri = new MongoClientURI(this.uriString);
        this.connectionClient = new MongoClient((uri));
        this.db = connectionClient.getDatabase(uri.getDatabase());
    }

    public void CloseClientConnection()
    {
        this.connectionClient.close();
    }

    public void AddAction(Action act)
    {
        try
        {
            MongoCollection coll = db.getCollection("action");

            List<Document> indexWeight = new ArrayList<Document>();
            for (Map.Entry<String, Object> entry : act.getdictionary().entrySet())
            {
                indexWeight.add(new Document(entry.getKey(), new Document("symbol", entry.getKey()).append("weight", entry.getValue())));
            }
            Document doc = new Document().append("name", act.getNom())
                    .append("codeISIN", act.getCodeISIN())
                    .append("prix", act.getPrix())
                    .append("date",act.getDate())
                    .append("volatility", act.getVolatility())
                    .append("volume",act.getVolume())
                    .append("cap_boursiere", act.getCapBoursiere())
                    .append("PER", act.getPER())
                    .append("indexWeight", (indexWeight))
                    ;




            coll.insertOne(doc);
        }
        catch (Exception e)
        {  System.out.println(e.getMessage());}

    }

    public void AddActionCollection(List<Action> collection)
    {
        List<Document> listDocuments = new ArrayList<Document>();
            MongoCollection coll = db.getCollection("action");

        for (Action a : collection)
        {
            List<Document> indexWeight = new ArrayList<Document>();
            for (Map.Entry<String, Object> entry : a.getdictionary().entrySet())
            {
                indexWeight.add(new Document(entry.getKey(), new Document("symbol", entry.getKey()).append("weight", entry.getValue())));
            }
            Document doc = new Document().append("name", a.getNom())
                    .append("codeISIN", a.getCodeISIN())
                    .append("prix", a.getPrix())
                    .append("date",a.getDate())
                    .append("volatility", a.getVolatility())
                    .append("volume",a.getVolume())
                    .append("cap_boursiere", a.getCapBoursiere())
                    .append("PER", a.getPER())
                    .append("indexWeight", (indexWeight))
                    ;
            listDocuments.add(doc);
        }
            coll.insertMany(listDocuments);

    }

    public void AddIndice(Indice ind)
    {
        try
        {
            MongoCollection coll = db.getCollection("indice");

            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date",ind.getDate())
                    .append("volume", ind.getVolume());
            coll.insertOne(doc);
        }
        catch (Exception e)
        {  System.out.println(e.getMessage());}


    }

    public Action FindAction(String CodeISIN)
    {
        try
        {
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
