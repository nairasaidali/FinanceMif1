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
public class Fenetre extends JFrame{
    //Instanciation d'un objet JPanel
    private JPanel JPanTitle = new JPanel();
    private JPanel jPan = new JPanel();
    private JPanel jPan2 = new JPanel();
    private JPanel jPan3 = new JPanel();
    private JPanel jPan4 = new JPanel();
    //Instanciation d'un bouton
    private JButton boutonValider = new JButton("Valider");
    //Instanciation d'un Label
    private JLabel labelChoix = new JLabel("Choix : ");
    //Instanciation d'un comboBox
    private JComboBox choixCACSBF = new JComboBox();
    private JComboBox choixActionIndice = new JComboBox();

    public Fenetre() {
        //D�finir un titre pour la fen�tre
        this.setTitle("Projet Finance");
        //D�finir sa taille
        this.setSize(400, 85);
        //Positionner la fen�tre au centre de la fen�tre
        this.setLocationRelativeTo(null);
        //Terminer le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Rendre visible la fen�tre
        this.setVisible(true);
        //Emp�cher le redimensionnement de la fen�tre
        this.setResizable(false);

        //Ajouter le label au JPanel, le comboBox au JPanel
        this.setLayout(new BorderLayout());

        JPanTitle.setVisible(true);
        jPan.add(labelChoix);
        jPan.add(choixCACSBF);


        setLayout(new FlowLayout());
        //jPan2.add(labelChoix2);
        jPan2.add(choixActionIndice);
        //D�finir taille
        choixCACSBF.setPreferredSize(new Dimension(70, 20));
        choixActionIndice.setPreferredSize(new Dimension(70, 20));

        //Ins�rer des valeurs dans le comboBox
        choixCACSBF.addItem("CAC 40");
        choixCACSBF.addItem("SBF 250");
        choixActionIndice.addItem("Action");
        choixActionIndice.addItem("Indice");

        //Ajouter les boutons aux JPanel
        jPan3.add(boutonValider);

        //Positionner les lignes en colonne
        jPan4.add(jPan);
        jPan4.add(jPan2);
        jPan4.add(jPan3);

        //Colorer le bouton en blanc
        boutonValider.setBackground(Color.WHITE);
        boutonValider.addActionListener(new BoutonValiderCAC());

        this.getContentPane().add(jPan4);

        this.setVisible(true);
    }

    class BoutonValiderCAC implements ActionListener {
        public void actionPerformed(ActionEvent argBouton) {
            if (choixCACSBF.getSelectedIndex() == 0 && choixActionIndice.getSelectedIndex() == 0) {
                new FenetreTableauAction().setVisible(true);
            }
        }
    }


}