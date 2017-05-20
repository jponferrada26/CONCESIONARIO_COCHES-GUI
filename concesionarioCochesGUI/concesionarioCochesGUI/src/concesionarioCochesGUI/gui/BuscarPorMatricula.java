package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCochesGUI.estructura.Coche;
import concesionarioCochesGUI.estructura.Fichero;
import concesionarioCochesGUI.estructura.exceptions.CocheNoExisteException;
import concesionarioCochesGUI.estructura.exceptions.MatriculaNoValidaException;
/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class BuscarPorMatricula extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BuscarPorMatricula() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Buscar por matricula");
		
		buttonBack.setVisible(false);
		buttonNext.setVisible(false);
		
		comboBoxModelo.removeAllItems();
		comboBoxMarca.removeAllItems();
		
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		
		btnAceptar.setText("Buscar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = Fichero.concesionario.get(textFieldMatricula.getText());
					comboBoxModelo.removeAllItems();
					comboBoxModelo.addItem(coche.getModelo());

					comboBoxMarca.removeAllItems();
					comboBoxMarca.addItem(coche.getModelo().getMarca());
					textFieldMatricula.setText(coche.getMatricula());
					
					seleccionarColor(coche.getColor());
					
				} catch (MatriculaNoValidaException | CocheNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}

}
