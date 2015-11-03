

//classe Actif
public abstract classe Actif{
private string Nom;
private double Valeur;
private string Date;
private long Volume;
private long CodeISIN;
 }
 // Classe Indice(héritant de la classe Actif)
public classe Indice extends Actif{

public Indice(){}

public Indice(string Nom,double Valeur,string Date,long Volume,long CodeISIN)
{
this.Nom= super.Nom;
this.Valeur=super.Valeur;
this.Date=super.Date;
this.Volume=super.Volume;
this.CodeISIN=super.codeISIN;
}

//GET
public string getNom(){
return this.Nom;
}
public string getValeur(){
return this.Valeur;
}

public string getDate(){
return this.Date;
}

public string getVolume(){
return this.Volume;
}

public string getcodeISIN(){
return this.codeISIN;
}
}

