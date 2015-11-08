package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;
import com.sdesilv4.model.Indice;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.Console;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.*;

/**
 * Created by Nicolas on 04/11/15.
 */


public class ControllerGestionIndice {
    private Indice indice;
    private String symbolIndex;

    public ControllerGestionIndice(String _symbolIndex)
    {
        this.indice = null;
        this.symbolIndex = _symbolIndex;

        getListOfActionObjects( getListOfSymbols());
    }

    public String httsGetMethodJson(String query)
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


    public List<String> getListOfSymbols()
    {
        String url = "https://query.yahooapis.com/v1/public/yql?q=use%20'https%3A%2F%2Fraw.githubusercontent.com%2FSoniaDjebali%2FFinanceMif1%2Fmaster%2Fyahoo.finance.components.xml'%20as%20mytable%3B%0Aselect%20*%20from%20mytable%20where%20symbol%3D'%5E" + this.symbolIndex + "'%3B&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";

        JSONObject obj = new JSONObject(httsGetMethodJson(url));

        List<String> list = new ArrayList<String>();
        JSONObject query = obj.getJSONObject("query");
        JSONObject results = query.getJSONObject("results");
        JSONArray array = results.getJSONArray("component");
        for(int i = 0 ; i < array.length() ; i++)
        {
            list.add(array.getString(i));
            System.out.println(array.getString(i));
        }
        return list;
    }

    public void getListOfActionObjects(List<String> listStocksSymbols)
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

        List<Action> listAction = new ArrayList<Action>();
        JSONObject query = obj.getJSONObject("query");
        JSONObject results = query.getJSONObject("results");
        JSONArray array = results.getJSONArray("quote");
        for(int i = 0 ; i < array.length() ; i++)
        {

            String symbol = array.getJSONObject(i).getString("symbol");
            String Name = array.getJSONObject(i).getString("Name");
            String ISIN = array.getJSONObject(i).getString("Name");
            double prix = (array.getJSONObject(i).getDouble("LastTradePriceOnly"));
            int volume = array.getJSONObject(i).getInt("Volume");
            String[] rawdate = array.getJSONObject(i).getString("LastTradeDate").split("/");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(rawdate[2]), Integer.parseInt(rawdate[0]), Integer.parseInt(rawdate[1]));
            Date date = calendar.getTime();

            double volatility = 0;                //to complete
            double MarketCap = 0;
            if (!array.getJSONObject(i).isNull("MarketCapitalization"))
                MarketCap = Double.parseDouble(array.getJSONObject(i).getString("MarketCapitalization").replaceAll("B",""))*1000000000;             //car string termine par B comme billion
            else
            {
                System.out.println("The market cap is not available on Yahoo Finance, please enter it manually (in billion) for : " + Name);
                Scanner scanner = new Scanner(System.in);
                MarketCap = Double.parseDouble(scanner.nextLine())*1000000000;
            }

            double PER = 0;                  //Je ne sais pas ce que c'est

            listAction.add(new Action(Name,ISIN,prix,volume,date,symbol,volatility,MarketCap,PER));
        }

        for(Action a : listAction)
        {
            System.out.println(a.toString());
        }
    }
}


