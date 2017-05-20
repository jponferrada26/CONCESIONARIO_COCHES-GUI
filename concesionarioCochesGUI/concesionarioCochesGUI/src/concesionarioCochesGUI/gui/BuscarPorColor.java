package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ListIterator;
import concesionarioCochesGUI.estructura.Coche;
import concesionarioCochesGUI.estructura.Fichero;

/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class BuscarPorColor extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ListIterator<Coche> list;
	private Coche coche;

	/**
	 * Create the dialog.
	 */
	public BuscarPorColor() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Buscar por color");

		textFieldMatricula.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		btnAceptar.setVisible(true);
		btnAceptar.setText("Buscar");
		rdbtnAzul.setEnabled(true);
		rdbtnRojo.setEnabled(true);
		rdbtnPlata.setEnabled(true);
		
		buttonBack.setEnabled(false);
		buttonNext.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxModelo.removeAllItems();
				comboBoxMarca.removeAllItems();
				textFieldMatricula.setText("");
				try{
					list = Fichero.concesionario.getCochesColor(getSelectedColor()).listIterator();
					comprobarBotones();
					mostrarCoche();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Selecciona un color", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				

			}
		});

		buttonBack.addActionListener(new ActionListener() {//Coche anterior
			public void actionPerformed(ActionEvent e) {
				if (list.hasPrevious()) {
					coche = list.previous();
					comprobarBotones();
					mostrarCoche();
					
				}
			}

		});

		buttonNext.addActionListener(new ActionListener() {//Coche siguiente
			public void actionPerformed(ActionEvent e) {
				if (list.hasNext()) {
					coche = list.next();
					
					comprobarBotones();
					mostrarCoche();
				}
			}
		});
	}

	/**
	 * Volcar los datos del coche en los elementos del panel(Mostrar coche)
	 */
	private void mostrarCoche() {//Mostrar coche
		comboBoxModelo.removeAllItems();
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxMarca.removeAllItems();
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		seleccionarColor(coche.getColor());
		textFieldMatricula.setText(coche.getMatricula());
	}
	
	/**
	 * Comprobar los botones del panel.
	 */
	void comprobarBotones() {
		if (!list.hasNext()) {
			buttonNext.setEnabled(false);
			coche = list.previous();
		} else {
			buttonNext.setEnabled(true);
		}
		if (!list.hasPrevious()) {
			buttonBack.setEnabled(false);
			coche = list.next();
		} else {
			buttonBack.setEnabled(true);
		}
	}
}
