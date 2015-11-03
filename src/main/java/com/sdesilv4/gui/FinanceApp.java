package com.sdesilv4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Created by sonia on 02/11/2015.
 */

public class FinanceApp {
    public static void main(String[] args) {

        //***** Cr�ation *****//
        //Cr�er une fen�tre
        JFrame fenetre = new JFrame();
        //Cr�er une ComboBox pour Action et Indice
        JComboBox comboBoxCACSBF = new JComboBox();
        //Cr�er un Label pour le ComboBox
        JLabel label = new JLabel("Indices : ");
        //Cr�er un Bouton pour valider le choix du ComboBox
        JButton bouton = new JButton("Valider");
        //Cr�er un container
        JPanel container = new JPanel();

        fenetre.setVisible(true);

        // D�finit le titre de la fen�tre
        fenetre.setTitle("Projet Finance");

        // D�finit taille de la fen�tre
        fenetre.setSize(500, 500);

        //Nous demandons maintenant � notre objet de se positionner au centre
        fenetre.setLocationRelativeTo(null);

        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.setLayout(new BorderLayout());
        comboBoxCACSBF.setPreferredSize(new Dimension(100, 20));

        JPanel top = new JPanel();

        //****** Insertion *****//
        //Insertion du Label
        top.add(label);
        //Insertion de la ComboBox
        top.add(comboBoxCACSBF);
        //Insertion du Bouton
        top.add(bouton);


        container.add(top, BorderLayout.NORTH);
        fenetre.setContentPane(container);
        fenetre.setVisible(true);

        //***** Modification Taille *****//
        //Taille de la ComboBox
        comboBoxCACSBF.setPreferredSize(new Dimension(110, 20));
        //Taille Bouton
        bouton.setPreferredSize(new Dimension(75, 20));

        //Ajouter �l�ments dans la liste
        comboBoxCACSBF.addItem("Choisir l'indice");
        comboBoxCACSBF.addItem("CAC 40");
        comboBoxCACSBF.addItem("SBF 120");

        //Couleur de la ComboBox
        comboBoxCACSBF.setBackground(Color.white);
    }
}
/**
 * Created by sonia on 02/11/2015.
 */

