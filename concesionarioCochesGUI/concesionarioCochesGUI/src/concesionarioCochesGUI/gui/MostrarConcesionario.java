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
	ListIterator<Coche> list = Fichero.concesionario.listIterator(Fichero.concesionario.size());
	private Coche coche;

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
		
		rdbtnAzul.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		
		buttonBack.setEnabled(false);
		textFieldMatricula.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.hasPrevious()) {
					coche = list.previous();
					mostrarCoche();
					comprobarBotones();
				}
			}

		});

		buttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.hasNext()) {
					coche = list.next();
					mostrarCoche();
					comprobarBotones();
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
