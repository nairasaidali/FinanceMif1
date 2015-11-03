package com.sdesilv4.gui;

<<<<<<< HEAD
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

public class FinanceApp{
    public static void main(String[] args) {

        //***** Création *****//
        //Créer une fenêtre
        JFrame fenetre = new JFrame();
        //Créer une ComboBox pour Action et Indice
        JComboBox comboBoxCACSBF = new JComboBox();
        //Créer un Label pour le ComboBox
        JLabel label = new JLabel("Indices : ");
        //Créer un Bouton pour valider le choix du ComboBox
        JButton bouton = new JButton("Valider");
        //Créer un container
        JPanel container = new JPanel();

        fenetre.setVisible(true);

        // Définit le titre de la fenêtre
        fenetre.setTitle("Projet Finance");

        // Définit taille de la fenêtre
        fenetre.setSize(500, 500);

        //Nous demandons maintenant à notre objet de se positionner au centre
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

        //Ajouter éléments dans la liste
        comboBoxCACSBF.addItem("Choisir l'indice");
        comboBoxCACSBF.addItem("CAC 40");
        comboBoxCACSBF.addItem("SBF 120");

        //Couleur de la ComboBox
        comboBoxCACSBF.setBackground(Color.white);
    }
=======
/**
 * Created by sonia on 02/11/2015.
 */
public class FinanceApp {
>>>>>>> 354e4c603fc701677506661d35d1de23b739920a
}
