package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCochesGUI.estructura.Fichero;

/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class BorrarCoche extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BorrarCoche() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Borrar coche");
		
		buttonBack.setVisible(false);
		buttonNext.setVisible(false);
		comboBoxMarca.setVisible(false);
		comboBoxModelo.setVisible(false);
		lblMarca.setVisible(false);
		lblModelo.setVisible(false);
		rdbtnAzul.setVisible(false);
		rdbtnPlata.setVisible(false);
		rdbtnRojo.setVisible(false);
		panelColor.setVisible(false);
		
		
		btnAceptar.setText("Borrar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Fichero.concesionario.eliminar(textFieldMatricula.getText());
					JOptionPane.showMessageDialog(contentPanel,"Se ha eliminado el coche correctamente.","",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					Fichero.concesionario.setModificado(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel,e1.getMessage(),"",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
	}

}
