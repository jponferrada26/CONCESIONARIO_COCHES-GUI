package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCochesGUI.estructura.Fichero;
import concesionarioCochesGUI.estructura.Modelo;

/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class AnadirCoche extends VentanaPadre{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public AnadirCoche() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("A�adir coche");
		
		buttonBack.setVisible(false);
		buttonNext.setVisible(false);
		
		btnAceptar.setText("A\u00F1adir");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Fichero.concesionario.annadir(textFieldMatricula.getText(),getSelectedColor(), (Modelo)comboBoxModelo.getSelectedItem());
					JOptionPane.showMessageDialog(null,"El coche ha sido añadido" , "Añadido",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					Fichero.concesionario.setModificado(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage() , "",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
	}

}
