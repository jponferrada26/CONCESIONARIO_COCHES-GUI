package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private java.util.ListIterator<Coche> list;
	
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
		btnAceptar.setText("Buscar");
		
		buttonBack.setEnabled(false);
		buttonNext.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxModelo.removeAllItems();
				comboBoxMarca.removeAllItems();
				textFieldMatricula.setText("");
				
				
				list = Fichero.concesionario.getCochesColor(getSelectedColor())
						.listIterator();
				if(Fichero.concesionario.getCochesColor(getSelectedColor()).size()==0){
					buttonBack.setEnabled(false);
					buttonNext.setEnabled(false);
				}else{
					buttonBack.setEnabled(true);
					buttonNext.setEnabled(true);
				}
				

			}
		});
		
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (list.hasPrevious()) {
					Coche cocheCopy = list.previous();
					if (Fichero.concesionario.getCochesColor(getSelectedColor()).indexOf(cocheCopy) > 0) {
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
					if (Fichero.concesionario.getCochesColor(getSelectedColor()).indexOf(cocheCopy) < Fichero.concesionario.getCochesColor(getSelectedColor()).size()-1)  {
						cocheCopy = list.next();
						mostrarCoche(cocheCopy);
						
					}else{
						mostrarCoche(cocheCopy);
					}
					comprobarBotones();
				}
			}
		});
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
}
