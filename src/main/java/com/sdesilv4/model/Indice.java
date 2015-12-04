package com.sdesilv4.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Indice extends Actif {
 private ArrayList<Action> collectionIndicePonderes;

 public Indice(String _nom, String _codeISIN, double _prix, double _volume, Date _date, String _symbol) {
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

    public Action findLessVolatileActionOfIndex()
  {
   double min_volatily = this.collectionIndicePonderes.get(0).getVolatility() ;
   Action a_min = new Action() ;

   for(Action a: this.collectionIndicePonderes)
   {
        if(a.getVolatility() <= min_volatily)
        {
           min_volatily = a.getVolatility();
           a_min = a;
        }
   }

     return a_min;
  }

    public Action findMostVolatileActionOfIndex()
 {
  double max_volatily = 0 ;
  Action a_max = new Action() ;

  for(Action a: this.collectionIndicePonderes)
  {
   if(a.getVolatility() >= max_volatily)
   {
     max_volatily = a.getVolatility();
     a_max = a;
   }
  }

  return a_max;
 }

    /* Remarque */

    //Pour le moment, ces deux derni�res fonctions renvoient l'action VIVENDI car il s'agit de la derni�re dans l'index du CAC 40
    //et toutes les volatilit�s �tant � 0, la condition d'in�galit� est toujours v�rifi�e et chaque action est � son tour stock�e comme ayant la volatilit� min (resp. max)
    //il faut en fait r�gler le probl�me dans la fonction getListOfActionObjects dans la classe ModelGestionIndiceFromYahoo.java
    //qui initialise la volatilit� � 0 et ne la r�cup�re pas encore (� noter: la volatilit� correspond � beta sur yahoo.finance et
    //l'info semble manquante pour les actions relatives au CAC 40 mais disponible pour l'indice am�ricain NASDAQ Composite par exemple)
}







