import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Interface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		  Instanciation des composants		
        final JFrame frame = new JFrame("Apprentissage Artificiel");
        final JPanel panel = new JPanel();
        final JButton btnFichier = new JButton("Fichier");
        final JFileChooser fileChooser = new JFileChooser();
        final JTextField txtFichier = new JTextField();
        final JComboBox<Attribute> comboAttr = new JComboBox();
        final JComboBox<String> comboVal = new JComboBox();

        comboAttr.setVisible(false);
        comboVal.setVisible(false);
        
//        Mise à jours de leurs attributs
        txtFichier.setPreferredSize(new Dimension(300, 26));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ARFF FILES", "arff", "arff");
        fileChooser.setFileFilter(filter);
        
//		  Fonctions des composants
        btnFichier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	int returnVal = fileChooser.showOpenDialog(frame);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fileChooser.getSelectedFile();
	                txtFichier.setText(file.getAbsolutePath());
	                System.out.println("Fichier sélectionné : " + file.getName());
	                System.out.println("Path : " + file.getAbsolutePath());
	                System.out.println("");

	        		Parser p = new Parser(file.getAbsolutePath());
	        		
	        		comboAttr.setVisible(true);
	        		for (Attribute attr : p.attributes) {
	        			comboAttr.addItem(attr);
					}        		
	            }
            }
        });
        
        // TODO : Ajouter les valeurs
        comboAttr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboVal.removeAllItems();
				//comboVal.addItem(e);
				comboVal.setVisible(true);
				System.out.println(e.getItem());
			}
		});
        
//        Ajout des composants au panel
        panel.add(btnFichier, BorderLayout.NORTH);
        panel.add(txtFichier, BorderLayout.NORTH);
        panel.add(comboAttr, BorderLayout.CENTER);
        panel.add(comboVal, BorderLayout.CENTER);
        
//        Ajout du panel dans la frame
        frame.getContentPane().add(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
//        Affichage
        frame.setVisible(true);
        
	}

}
