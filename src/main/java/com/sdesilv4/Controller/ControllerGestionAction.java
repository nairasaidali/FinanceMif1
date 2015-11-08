package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nicolas on 07/11/15.
 */
public class ControllerGestionAction {
    private Action action;
    private String symbolAction;

    public ControllerGestionAction(String _symbol)
    {
        this.symbolAction = _symbol;
        action = null;
    }

    public String makeHttpsGetIndexComponentsJson()
    {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
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
}
