package com.sdesilv4.Controller;

import com.sdesilv4.model.Action;

/**
 * Created by Nicolas on 07/11/15.
 */
public class ControllerGestionAction {
    private Action action;
    private String symbolAction;

    public ControllerGestionAction(String _symbol)
    {
        this.symbolAction = _symbol;

    }

   // https://query.yahooapis.com/v1/public/yql?q=use%20'https%3A%2F%2Fraw.githubusercontent.com%2Fphilippn%2Fyql-tables%2Fmaster%2Fyahoo.finance.components.xml'%20as%20mytable%3B%0Aselect%20*%20from%20mytable%20where%20symbol%3D'%5EFCHI'%3B&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=
}
