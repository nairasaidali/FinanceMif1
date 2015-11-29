package com.sdesilv4.model;

import java.util.Date;

abstract class Actif {

    private String nom;
    private String codeISIN;
    private String symbol;
    private double prix;
    private double volume;
    private Date date;


    public Actif(String _nom, String _codeISIN, double _prix, double _volume, Date _date, String _symbol){
        this.nom =_nom;
        this.codeISIN=_codeISIN;
        this.prix=_prix;
        this.volume = _volume;
        this.date= _date;
        this.symbol = _symbol;
    }

    public String getNom(){
        return this.nom;
    }
    public String getCodeISIN(){
        return this.codeISIN;
    }
    public double getPrix(){
        return this.prix;
    }
    public double getVolume(){
        return this.volume;
    }
    public Date getDate(){
        return this.date;
    }
    public String getSymbol(){
        return this.symbol;
    }


}
