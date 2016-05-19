import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	            }
            }
        });
        
//        Ajout des composants au panel
        panel.add(btnFichier, BorderLayout.NORTH);
        panel.add(txtFichier, BorderLayout.NORTH);
        
//        Ajout du panel dans la frame
        frame.getContentPane().add(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
//        Affichage
        frame.setVisible(true);
        
	}

}
