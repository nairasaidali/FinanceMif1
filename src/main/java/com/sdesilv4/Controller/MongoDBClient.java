package com.sdesilv4.Controller;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sdesilv4.model.Action;
import com.sdesilv4.model.Indice;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Bizeray on 06/11/2015.
 */
public class MongoDBClient {
    private String uriString;
    private MongoClient connectionClient;
    private MongoClientURI uri;
    private MongoDatabase db;

    public MongoDBClient(String _uriString) {
        this.uriString = _uriString;
        this.uri = new MongoClientURI(this.uriString);
        this.connectionClient = new MongoClient((uri));
        this.db = connectionClient.getDatabase(uri.getDatabase());
    }

    public void CloseClientConnection() {
        this.connectionClient.close();
    }

    public void AddAction(Action act) {

            MongoCollection coll = db.getCollection("action");

            List<Document> indexWeight = new ArrayList<Document>();
            for (Map.Entry<String, Object> entry : act.getdictionary().entrySet()) {
                indexWeight.add(new Document(entry.getKey(), new Document("symbol", entry.getKey()).append("weight", entry.getValue())));
            }
            Document doc = new Document().append("name", act.getNom())
                    .append("codeISIN", act.getCodeISIN())
                    .append("prix", act.getPrix())
                    .append("date", act.getDate())
                    .append("volatility", act.getVolatility())
                    .append("volume", act.getVolume())
                    .append("cap_boursiere", act.getCapBoursiere())
                    .append("PER", act.getPER())
                    .append("indexWeight", (indexWeight));



            coll.insertOne(doc);


    }

    public void AddActionCollection(List<Action> collection) {
        List<Document> listDocuments = new ArrayList<Document>();
        MongoCollection coll = db.getCollection("action");

        for (Action a : collection) {
            List<Document> indexWeight = new ArrayList<Document>();
            for (Map.Entry<String, Object> entry : a.getdictionary().entrySet()) {
                indexWeight.add(new Document("symbol", entry.getKey()).append("weight", entry.getValue()));
            }
            Document doc = new Document().append("name", a.getNom())
                    .append("codeISIN", a.getCodeISIN())
                    .append("prix", a.getPrix())
                    .append("date", a.getDate())
                    .append("volatility", a.getVolatility())
                    .append("volume", a.getVolume())
                    .append("cap_boursiere", a.getCapBoursiere())
                    .append("PER", a.getPER())
                    .append("indexWeight", (indexWeight));
            listDocuments.add(doc);
        }
        coll.insertMany(listDocuments);

    }

    public void AddIndice(Indice ind) {
                MongoCollection coll = db.getCollection("indice");
            List<Document> listSymbolAction = new ArrayList<Document>();
            for (Action a : ind.GetCollectionAction()) {
                listSymbolAction.add(new Document("symbol", a.getSymbol()).append("name", a.getNom()).append("price", a.getPrix()).append("date",a.getDate()));
            }

            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date", ind.getDate())
                    .append("volume", ind.getVolume())
                    .append("components", listSymbolAction);

            coll.insertOne(doc);
            AddActionCollection(ind.GetCollectionAction());



    }
    public ArrayList<Action> GetAction_Indice(final String indice_symbole)    // toutes les actions liées à un indice
    {
        final ArrayList<Action> ActionList=new ArrayList<Action>();

        MongoCollection coll = db.getCollection("action");
        String filter= "indexWeight."+indice_symbole+".symbol";
        FindIterable<Document> g = coll.find(new Document(filter,indice_symbole ));

        g.forEach(new Block<Document>() {

            public void apply(final Document document) {


                String name = (String) document.get("name");
                String codeISIN = (String) document.get("codeISIN");
                Double prix = (Double) document.get("prix");
                Date date = (Date) document.get("date");
                Long volume = (Long) document.get("volume");
                Double volatility = (Double) document.get("volatility");
                Double cap_boursiere = (Double) document.get("cap_boursiere");
                Double PER = (Double) document.get("PER");

                Action act = new Action(name, codeISIN, prix, volume, date,indice_symbole, volatility, cap_boursiere, PER);
                ActionList.add(act);

            }
        });
        //Affichage si besoin
        System.out.println("Nombre d'action "+ActionList.size());
        for(Action item : ActionList)
        {
            System.out.println("Tous les actions du "+ActionList.size());
            System.out.println(item.toString());
        }
        return  ActionList;

    }
    public Action FindAction(String CodeISIN) {
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

        Action act = new Action(name, codeISIN, prix, volume, date, "symbol", volatility, cap_boursiere, PER);    //symbol a compléter !!
        return act;

    }




    public Indice FindIndice(String CodeISIN) {
        MongoCollection coll = db.getCollection("indice");

        FindIterable<Document> g = coll.find(new Document("codeISIN", CodeISIN));
        String name = (String) g.first().get("name");
        String codeISIN = (String) g.first().get("codeISIN");
        Double prix = (Double) g.first().get("prix");
        Date date = (Date) g.first().get("date");
        Long volume = (Long) g.first().get("volume");
        Double volatility = (Double) g.first().get("volatility");

        Indice ind = new Indice(name, codeISIN, prix, volume, date, "symbol !! "); //symbol a compléter !!
        return ind;

    }

    public String UpdateAction(Action act) {

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




        return "action bien modifié";
    }

    public String UpdateIndice(Indice ind) {


            MongoCollection coll = db.getCollection("indice");

            Document doc = new Document().append("name", ind.getNom())
                    .append("codeISIN", ind.getCodeISIN())
                    .append("prix", ind.getPrix())
                    .append("date",ind.getDate())
                    .append("volume", ind.getVolume());

            coll.updateOne(new Document("codeISIN", ind.getCodeISIN()), doc);



        return "indice bien modifié";
    }
}
