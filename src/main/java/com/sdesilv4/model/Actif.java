package com.sdesilv4.model;

import java.util.Date;

abstract class Actif {

    private String nom;
    private String codeISIN;
    private double prix;
    private long volume;
    private Date date;


    public String getNom(){
        return nom;
    }
    public String getCodeISIN(){
        return codeISIN;
    }
    public double getPrix(){
        return prix;
    }
    public long getVolume(){
        return volume;
    }
    public Date getDate(){
        return date;
    }





    public Actif(String _nom, String _codeISIN, double _prix, long _volume, Date _date){
        _nom = this.getNom();
        _codeISIN = this.getCodeISIN();
        _prix = this.getPrix();
        _volume = this.getVolume();
        _date = this.getDate();
    }
}
