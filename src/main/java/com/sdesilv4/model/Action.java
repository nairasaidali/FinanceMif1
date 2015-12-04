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
    Map<String, Object> dictionaryIndiceWeight;

    public Action(String _nom, String _codeISIN, double _prix, double _volume, Date _date, String _symbol, double _volatility, double _cap_boursiere, double _PER) {
        super(_nom, _codeISIN, _prix, _volume, _date, _symbol);
        this.volatility = _volatility;
        this.cap_boursiere = _cap_boursiere;
        this.PER = _PER;
        dictionaryIndiceWeight = new HashMap<String, Object>();

    }

    public Action()
    {

    }

    public Map<String, Object> getdictionary()
    {
        return this.dictionaryIndiceWeight;
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

    public void addIndiceWeight(String indexSymbol, double p)
    {
        dictionaryIndiceWeight.put(indexSymbol, p);
    }

    public double GetWeight(String  indexSymbol)
    {
        return new Double(dictionaryIndiceWeight.get(indexSymbol).toString());
    }

    public String toString()
    {
        String chaine;
        chaine =  "-- L'action est caracterisée par:" ;
        chaine += "\n";
        chaine += "- Nom: " + this.getNom();
        chaine += "\n";
        chaine += "- Volatilite: " + this.volatility;
        chaine += "\n";
        chaine += "- Capitalisation boursiere: " + this.cap_boursiere;
        chaine += "\n";
        chaine += "- PER: " + this.PER;
        chaine += "\n";
        chaine += "- Prix: " + this.getPrix();
        return chaine;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Action){
            Action toCompare = (Action) o;
            return this.getSymbol().equals(toCompare.getSymbol());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getSymbol().hashCode();
    }

}

