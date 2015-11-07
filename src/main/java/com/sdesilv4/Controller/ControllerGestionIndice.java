package com.sdesilv4.Controller;

import com.sdesilv4.model.Indice;
import org.bson.json.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

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
        getListOfSymbols();
    }

    private String makeHttpsGetIndexComponentsJson(String symbolIndex)
    {
        String url = "https://query.yahooapis.com/v1/public/yql?q=use%20'https%3A%2F%2Fraw.githubusercontent.com%2Fphilippn%2Fyql-tables%2Fmaster%2Fyahoo.finance.components.xml'%20as%20mytable%3B%0Aselect%20*%20from%20mytable%20where%20symbol%3D'%5E" + symbolIndex + "'%3B&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";
        String outPutJson;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            outPutJson = response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(outPutJson);
        return outPutJson;
    }

    private List<String> getListOfSymbols()
    {
        JSONObject obj = new JSONObject(makeHttpsGetIndexComponentsJson(symbolIndex));

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
}


