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
        //Définir un titre pour la fenêtre
        this.setTitle("Projet Finance");
        //Définir sa taille
        this.setSize(400, 85);
        //Positionner la fenêtre au centre de la fenêtre
        this.setLocationRelativeTo(null);
        //Terminer le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Rendre visible la fenêtre
        this.setVisible(true);
        //Empêcher le redimensionnement de la fenêtre
        this.setResizable(false);

        //Ajouter le label au JPanel, le comboBox au JPanel
        this.setLayout(new BorderLayout());

        JPanTitle.setVisible(true);

        //Placer composants dans une grid
        this.setLayout(new GridLayout(1, 4, 20, 50));

        this.getContentPane().add(labelChoix);
        this.getContentPane().add(choixCACSBF);
        this.getContentPane().add(choixActionIndice);
        this.getContentPane().add(boutonValider);

        labelChoix.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centrer le texte

        this.setVisible(true);

        //Définir taille
        choixCACSBF.setPreferredSize(new Dimension(70, 20));
        choixActionIndice.setPreferredSize(new Dimension(70, 20));

        //Insérer des valeurs dans le comboBox
        choixCACSBF.addItem("CAC 40");
        choixCACSBF.addItem("SBF 250");
        choixActionIndice.addItem("Action");
        choixActionIndice.addItem("Indice");

        //Colorer le bouton en blanc
        //boutonValider.setBackground(Color.WHITE);
        boutonValider.addActionListener(new BoutonValiderCAC());

        //this.getContentPane().add(jPan4);

        this.setVisible(true);
    }

    // Pour créer une bordure entre la fenetre et la GridLayout
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top +10, normal.left + 10, normal.bottom + 20, normal.right + 20);
    }

    // Action au déclenchement d'un evenement
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==boutonValider)
            if(choixCACSBF.getSelectedItem().toString() == "Choisir l'indice"){
                JOptionPane.showMessageDialog(this,"Veuillez choisir un indice.", "Indice non renseigne", JOptionPane.WARNING_MESSAGE);
            }else if(choixCACSBF.getSelectedItem().toString() == "CAC 40"){
                // action si CAC 40

                // par exemple on veut afficher le tableau dans une nouvelle fenetre
              //  actions.setVisible(true);

            }else if(choixCACSBF.getSelectedItem().toString() == "SBF 120"){
                // action si SBF 120
            }
    }

    class BoutonValiderCAC implements ActionListener {
        public void actionPerformed(ActionEvent argBouton) {
            if (choixCACSBF.getSelectedIndex() == 0 && choixActionIndice.getSelectedIndex() == 0) {
                new FenetreTableauAction().setVisible(true);
            }
            else if (choixCACSBF.getSelectedIndex() == 0 && choixActionIndice.getSelectedIndex() == 1) {
                new TableauIndice().setVisible(true);
            }
        }
    }


}
