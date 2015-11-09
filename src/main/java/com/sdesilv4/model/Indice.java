package com.sdesilv4.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Indice extends Actif {
 private ArrayList<Action> collectionIndicePonderes;

 public Indice(String _nom, String _codeISIN, double _prix, long _volume, Date _date, String _symbol) {
  super(_nom, _codeISIN, _prix, _volume, _date, _symbol);
  this.collectionIndicePonderes = new ArrayList<Action>();
 }

 public void addACtion(Action a, double weight)
 {
  collectionIndicePonderes.add(a);
 }

 public List<Action> GetCollectionAction()
 {
  return this.collectionIndicePonderes;
 }

 public String toString() {
  String out = "L'indice est " + this.getNom();
  out += ", le code ISIN est : " + this.getCodeISIN();
  out += ", le prix est : " + this.getPrix();
  out += ", il contient les actions suivantes ";
  out+= "\n";

  for (Action a : this.collectionIndicePonderes)
  {
   out += "---";
   out+= a.getNom();
   out+= "| PRIX : "+ a.getPrix();
   out+= "\n";
  }
  return out;
 }

}







