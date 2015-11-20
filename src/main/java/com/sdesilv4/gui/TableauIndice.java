package com.sdesilv4.gui;
import javax.swing.*;
import javax.swing.JFrame;

/**
 * Created by Sabrina on 20/11/2015.
 */
public class TableauIndice extends JFrame{

    public TableauIndice(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTable");
        this.setSize(800, 180);
        this.setLocationRelativeTo(null);

        Object[][] valeur = {
                //Exemple de valeurs possibles
                {"BNP Paribas", "BNP.PA", "56.55", "-1.51", "2 708 045", "19/11/15"},
                {"Societe Generale Group", "GLE.PA", "44.24", "-1.23","2 024 429", "19/11/15" },
                {"ENGIE SA", "GSZ.PA", "17.56", "3.97", "4 458 401", "19/11/15"}

        };

        String indiceTitle[] = {"Indice", "Symbole", "Prix","Variation(%)","Volume", "date"};

        JTable tableauIndice = new JTable(valeur, indiceTitle);
        this.getContentPane().add(new JScrollPane(tableauIndice));
        tableauIndice.setVisible(true);
    }
}
