package com.sdesilv4.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by paard consulting on 10/11/2015.
 */
public class FenetreTableauAction extends JFrame{
    public FenetreTableauAction(){
        //Définir un titre pour la fenêtre
        this.setTitle("Recapitulatif Actions");
        //Définir sa taille
        this.setSize(700, 150);
        //Positionner la fenêtre au centre de la fenêtre
        this.setLocationRelativeTo(null);
        //Rendre visible la fenêtre
        this.setVisible(true);
        //Empêcher le redimensionnement de la fenêtre
        this.setResizable(false);

        // Déclaration manuelle des valeurs du tableau des actions
        Object[][] actionData = {
                {"dg","dsh","dsh", "dsh", "sdg", "sdg"},
                {"dg","dsh","dsh", "dsh", "sdg", "sdg"},
                {"dg","dsh","dsh", "dsh", "sdg", "sdg"},
                {"dg","dsh","dsh", "dsh", "sdg", "sdg"},
                {"dg","dsh","dsh", "dsh", "sdg", "sdg"}
        };
        String actionTitle[] = {"Nom", "Valeur", "Poids", "Ticher", "Prix", "Date d'evaluation"};
        JTable tableauAction = new JTable(actionData, actionTitle);
        this.getContentPane().add(new JScrollPane(tableauAction));
        tableauAction.setVisible(true);


        // Couleur du tableau
        tableauAction.setBackground(Color.white);

    }
}
