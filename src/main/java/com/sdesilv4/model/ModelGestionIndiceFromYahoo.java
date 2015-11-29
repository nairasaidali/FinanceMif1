package com.sdesilv4.model;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Nicolas on 04/11/15.
 */


public class ModelGestionIndiceFromYahoo {
    private Indice indice;
    private String symbolIndex;
    private List<Action> listAction;

    public ModelGestionIndiceFromYahoo(String _symbolIndex)
    {
        this.indice = null;
        this.symbolIndex = _symbolIndex;
        this.listAction = new ArrayList<Action>();

        getListOfActionObjects(getListOfSymbols());
        ReparitionDesPoids();
        IndexCreation();
    }

    public String httsGetMethodJson(String query)            //Methode pour faire un HTTP get d'une URL
    {
        try {
            URL obj = new URL(query);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + query);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            return response.toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> getListOfSymbols()                           //Méthode permettant de récupérer la liste des symboles d'action de l'indice donné.
    {

        String url = "";
        try {
             url = "https://query.yahooapis.com/v1/public/yql?q=use%20'https%3A%2F%2Fraw.githubusercontent.com%2FSoniaDjebali%2FFinanceMif1%2Fmaster%2Fyahoo.finance.components.xml'%20as%20mytable%3B%0Aselect%20*%20from%20mytable%20where%20symbol%3D'" + URLEncoder.encode(symbolIndex, "UTF-8") + "'%3B&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";
        }
        catch (UnsupportedEncodingException e) {
        }
        JSONObject obj = new JSONObject(httsGetMethodJson(url));

        List<String> list = new ArrayList<String>();
        JSONObject query = obj.getJSONObject("query");
        JSONObject results = query.getJSONObject("results");
        JSONArray array = results.getJSONArray("component");
        for(int i = 0 ; i < array.length() ; i++)
        {
            list.add(array.getString(i));
        }
        return list;
    }

    public void getListOfActionObjects(List<String> listStocksSymbols)                                   //Méthode permettant de récupérer la liste des actions récupérées ainsi que leurs propriétées (prix, date, volume,etc) et de les mettre en objets d'actions.
    {
        String requeteStocks ="";
        boolean first = true;
        for (String s : listStocksSymbols)
        {
            if (!first)
                requeteStocks += "%2C"+"%22"+s+"%22";
            else
            {
                requeteStocks += "%22"+s+"%22";
                first=!first;
            }
        }
        String HttpRequest = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(" + requeteStocks +")&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";

        JSONObject obj = new JSONObject(httsGetMethodJson(HttpRequest));
        JSONObject query = obj.getJSONObject("query");
        JSONObject results = query.getJSONObject("results");
        JSONArray array = results.getJSONArray("quote");
        for(int i = 0 ; i < array.length() ; i++)
        {

            String symbol = array.getJSONObject(i).getString("symbol");
            String Name = array.getJSONObject(i).getString("Name");
            String ISIN = "Not available in Yahoo API";
            double prix = (array.getJSONObject(i).getDouble("LastTradePriceOnly"));
            double volume = array.getJSONObject(i).getDouble("Volume");
            String[] rawdate = array.getJSONObject(i).getString("LastTradeDate").split("/");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(rawdate[2]), Integer.parseInt(rawdate[0]), Integer.parseInt(rawdate[1]));
            Date date = calendar.getTime();

            double volatility = 0;                //to complete
            double MarketCap = 0;
            if (!array.getJSONObject(i).isNull("MarketCapitalization"))
                MarketCap = Double.parseDouble(array.getJSONObject(i).getString("MarketCapitalization").replaceAll("B",""))*1000000000;        //car string termine par B comme billion
            else
                MarketCap = ModelGestionAction.MarketCapt(symbol);

            double PER = 0;

            if (!array.getJSONObject(i).isNull("PERatio"))
            {
                 PER = (array.getJSONObject(i).getDouble("PERatio"));

            }

            this.listAction.add(new Action(Name,ISIN,prix,volume,date,symbol,volatility,MarketCap,PER));
        }
    }

    public void ReparitionDesPoids()                  //Les poids des actions n'étant pas renseignés directement dans l'API Finance Yahoo, nous devons les calculer manuellement..
    {
        double globalMarketCapIndex = 0;
        for (Action a : listAction)
        {
            globalMarketCapIndex += a.getCapBoursiere();
        }
        for (Action a : listAction)
        {
            a.addIndiceWeight(symbolIndex,a.getCapBoursiere()/globalMarketCapIndex);
        }
    }

    public void IndexCreation()                //Création de l'indice final
    {
        String HttpRequest = "";
        try {
             HttpRequest = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" + URLEncoder.encode(symbolIndex, "UTF-8")  + "%22)&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";
        }
        catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 is unknown");
        }
            JSONObject obj = new JSONObject(httsGetMethodJson(HttpRequest));
            JSONObject query = obj.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject array = results.getJSONObject("quote");
            String symbol = array.getString("symbol");
            String Name = array.getString("Name");
            String ISIN = "Not available in Yahoo API";
            double prix = (array.getDouble("LastTradePriceOnly"));
        double volume = array.getDouble("Volume");
            String[] rawdate = array.getString("LastTradeDate").split("/");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(rawdate[2]), Integer.parseInt(rawdate[0]), Integer.parseInt(rawdate[1]));
            Date date = calendar.getTime();

            double volatility = -1;                //to complete
            double MarketCap = -1;

            this.indice = new Indice(Name,ISIN,prix,volume,date,symbol);
            for (Action a : listAction)
            {
                this.indice.addACtion(a,a.GetWeight(symbolIndex));
            }
        }


    public Indice GetIndex()
    {
        return this.indice;
    }
}


