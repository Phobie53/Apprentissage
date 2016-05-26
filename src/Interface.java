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
	public static Parser p;
	public static FOIL f;
	public static void main(String[] args) {
		
//		  Instanciation des composants		
        final JFrame frame = new JFrame("Apprentissage Artificiel");
        final JPanel panel = new JPanel();
        final JButton btnFichier = new JButton("Fichier");
        final JFileChooser fileChooser = new JFileChooser();
        final JTextField txtFichier = new JTextField();
        final JComboBox<String> combo = new JComboBox();
        final JTextPane txtRegles = new JTextPane();

        combo.setVisible(false);
        
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

	        		p = new Parser(file.getAbsolutePath());
	        		
	        		combo.setVisible(true);
	        		for (Attribute attr : p.attributes) {
	        			for(String val : attr.getValues())
	        			combo.addItem(attr.toString() + " = " + val);
					}        		
	            }
            }
        });
        
//		  Sélection de l'attribut voulu
        combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Sélection de : " + e.getItem());
					String itemSelected = (String) e.getItem();
					String[] tab = itemSelected.split("=");
					f = new FOIL(p.pos, p.neg, p.litteraux, new Fait(tab[0],tab[1]));
					txtRegles.setText("");
					for (Regle rgl : f.getRegles()) {
						txtRegles.setText(txtRegles.getText() + rgl + "\n");
					}
					f = null;
				}
			}
		});
        
//        Ajout des composants au panel
        panel.add(btnFichier, BorderLayout.NORTH);
        panel.add(txtFichier, BorderLayout.NORTH);
        panel.add(combo, BorderLayout.CENTER);
        panel.add(txtRegles, BorderLayout.CENTER);
        
//        Ajout du panel dans la frame
        frame.getContentPane().add(panel);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
//        Affichage
        frame.setVisible(true);
        
	}

}
