/**
 * Created by Naira on 02/11/2015.
 */
package com.sdesilv4.model;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Action extends Actif{

    private double volatility;
    private double cap_boursiere;
    private double PER;
    Map<Indice, Object> dictionary;

    public Action(String _nom, String _codeISIN, double _prix, long _volume, Date _date, String _symbol, double _volatility, double _cap_boursiere, double _PER) {
        super(_nom, _codeISIN, _prix, _volume, _date, _symbol);
        this.volatility = _volatility;
        this.cap_boursiere = _cap_boursiere;
        this.PER = _PER;
        dictionary = new HashMap<Indice, Object>();




    }

    public Map<Indice, Object> getdictionary()
    {
        return this.dictionary;
    }

    public double getVolatility()
    {
        return this.volatility;
    }

    public double getPER()
    {
        return this.PER;
    }

    public double getCapBoursiere()
    {
        return this.cap_boursiere;
    }

//    public void setPoids(double _poids)
//    {
//        this.poids = _poids;
//    }
//
//    public void setVolatility(double _volatility)
//    {
//        this.volatility = _volatility ;
//    }
//
//    public void setPER(double _PER)
//    {
//        this.PER = _PER;
//    }
//
//    public void setCapBoursiere(double _cap_boursiere)
//    {
//        this.cap_boursiere = _cap_boursiere;
//    }

    public void addIndiceWeight(Indice i, double p)
    {
        dictionary.put(i, p);
    }

    public void poids(Indice i)
    {
        System.out.println(dictionary.get(i));
    }
    public String toString()
    {
        String chaine;
        chaine =  "-- L'action est caracterisée par:" ;
        chaine += "\n";
        chaine += "- Nom: " + this.getNom();
        // chaine += "- Poids: " + this.poids;
        chaine += "\n";
        chaine += "- Volatilite: " + this.volatility;
        chaine += "\n";
        chaine += "- Capitalisation boursiere: " + this.cap_boursiere;
        chaine += "\n";
        chaine += "- PER: " + this.PER;

        return chaine;
    }
}

