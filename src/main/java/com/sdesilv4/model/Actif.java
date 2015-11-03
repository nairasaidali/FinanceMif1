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


    public void setPrix(double valeur){
        prix = valeur;
    }
    public void setDate(Date valeur){
        date = valeur;
    }
    public void setVolume(long valeur){
        volume = valeur;
    }


    public Actif(String Nom, String CodeISIN, double Prix, long Volume, Date Date){
        Nom = this.getNom();
        CodeISIN = this.getCodeISIN();
        Prix = this.getPrix();
        Volume = this.getVolume();
        Date = this.getDate();
    }
}
