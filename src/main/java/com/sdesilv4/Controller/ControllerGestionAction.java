package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

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
    static double MarketCapt(String symbol)
    {
        double billion = 1000000000;

        if (symbol.equals("AIR.PA"))
            return 50.89*billion;
        else if (symbol.equals("LG.PA"))
            return 17.319*billion;
        else if (symbol.equals("MT.PA"))
            return 8.327*billion;
        else if (symbol.equals("SOLB.PA"))
            return 8.86*billion;
        else if (symbol.equals("UL.PA"))
            return 24.31516*billion;
        else
        {
            System.out.println("The market cap is not available on Yahoo Finance, please enter it manually (in billion) for : " + symbol);
            Scanner scanner = new Scanner(System.in);
            return Double.parseDouble(scanner.nextLine())*1000000000;
        }

    }
}


