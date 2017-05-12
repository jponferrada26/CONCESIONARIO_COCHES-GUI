package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCochesGUI.estructura.Coche;
import concesionarioCochesGUI.estructura.Fichero;

/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class MostrarConcesionario extends VentanaPadre {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ListIterator<Coche> list = Fichero.concesionario.listIterator(Fichero.concesionario.size());

	/**
	 * Create the dialog.
	 */
	public MostrarConcesionario() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Mostrar concesionario");
		
		mostrarPrimeraVez();
		rdbtnAzul.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		btnAceptar.setVisible(false);
		textFieldMatricula.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.hasPrevious()) {
					Coche cocheCopy = list.previous();
					if (Fichero.concesionario.indexOf(cocheCopy) > 1) {
						cocheCopy = list.previous();
						mostrarCoche(cocheCopy);
						
					}else{
						mostrarCoche(cocheCopy);
					}
					comprobarBotones();
				}
			}

		});
		
		buttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.hasNext()) {
					Coche cocheCopy = list.next();
					if (Fichero.concesionario.indexOf(cocheCopy) < Fichero.concesionario.size()-1) {
						cocheCopy = list.next();
						mostrarCoche(cocheCopy);
						
					}else{
						mostrarCoche(cocheCopy);
					}
					comprobarBotones();
				}
			}


			
		});
		
		btnCancelar.setText("Aceptar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		
	}

	private void mostrarPrimeraVez() {
		if (Fichero.concesionario.size()>0) {
			Coche cocheCopy =list.next();
			comboBoxModelo.removeAllItems();
			comboBoxModelo.addItem(cocheCopy.getModelo());
			
			comboBoxMarca.removeAllItems();
			comboBoxMarca.addItem(cocheCopy.getModelo().getMarca());
			seleccionarColor(cocheCopy.getColor());
			textFieldMatricula.setText(cocheCopy.getMatricula());
			
		}
		
		
		
	}
	
	/**
	 * Inserta en los Jtext, los datos del coche seleccionado
	 * @param cocheCopy
	 */
	private void mostrarCoche(Coche cocheCopy) {
		comboBoxModelo.removeAllItems();
		comboBoxModelo.addItem(cocheCopy.getModelo());
		
		comboBoxMarca.removeAllItems();
		comboBoxMarca.addItem(cocheCopy.getModelo().getMarca());
		seleccionarColor(cocheCopy.getColor());
		textFieldMatricula.setText(cocheCopy.getMatricula());
	}
	
	/**
	 * Comprueba los botones para habilitarlos/deshabilitarlos
	 */
	private void comprobarBotones() {
		if (!list.hasNext()) {
			buttonNext.setEnabled(false);
		}else{
			buttonNext.setEnabled(true);
		}
		if(!list.hasPrevious()){
			buttonBack.setEnabled(false);
		}else{
			buttonBack.setEnabled(true);
		}
	}
}
