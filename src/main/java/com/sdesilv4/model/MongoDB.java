package com.sdesilv4.model;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

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
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("action");
            Document doc = new Document().append("name", act.getNom())
                    .append("codeISIN", act.getCodeISIN())
                    .append("prix", act.getPrix())
                    .append("date",act.getDate())
                    .append("volatility", act.getVolatility())
                    .append("volume",act.getVolume())
                    .append("cap_boursiere", act.getCapBoursiere())
                    .append("PER", act.getPER());
            db.getCollection("action").insertOne(doc);
        }
        catch (Exception e)
        { return e.getMessage();}

        return "action bien ajoute";

    }

    public String AddIndice(Indice ind)
    {
        try
        {
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("indice");
            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date",ind.getDate())
                    .append("volume", ind.getVolume());
            db.getCollection("action").insertOne(doc);
        }
        catch (Exception e)
        { return e.getMessage();}

        return "indice bien ajoute";

    }

    public Action FindAction(String CodeISIN)
    {
        try
        {
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("action");
            FindIterable<Document> g = coll.find(new Document("codeISIN", CodeISIN));
            String name = (String) g.first().get("name");
            String codeISIN = (String) g.first().get("codeISIN");
            Double prix = (Double) g.first().get("prix");
            Date date = (Date) g.first().get("date");
            Long volume = (Long) g.first().get("volume");
            Double volatility = (Double) g.first().get("volatility");
            Double cap_boursiere = (Double) g.first().get("cap_boursiere");
            Double PER = (Double) g.first().get("PER");
            Action act = new Action (name,codeISIN,prix,volume,date,volatility,cap_boursiere,PER);
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
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("indice");
            FindIterable<Document> g = coll.find(new Document("codeISIN", CodeISIN));
            String name = (String) g.first().get("name");
            String codeISIN = (String) g.first().get("codeISIN");
            Double prix = (Double) g.first().get("prix");
            Date date = (Date) g.first().get("date");
            Long volume = (Long) g.first().get("volume");
            Double volatility = (Double) g.first().get("volatility");

            Indice ind = new Indice(name,codeISIN,prix,volume,date);
            return ind;
        }
        catch(Exception e) {
            return null;
        }
    }

    public String UpdateAction(Action act) {
        try {
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("action");
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
            MongoClient mongoClient = new MongoClient();
            MongoDatabase db = mongoClient.getDatabase("actif");
            MongoCollection<Document> coll = db.getCollection("indice");
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
