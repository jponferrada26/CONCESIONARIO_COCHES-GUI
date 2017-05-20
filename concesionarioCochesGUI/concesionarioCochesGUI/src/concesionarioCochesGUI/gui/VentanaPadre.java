package concesionarioCochesGUI.gui;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import concesionarioCochesGUI.estructura.Fichero;
import concesionarioCochesGUI.estructura.Marca;
import concesionarioCochesGUI.estructura.Modelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class VentanaPadre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroupColors = new ButtonGroup();
	JTextField textFieldMatricula;
	JRadioButton rdbtnPlata;
	JRadioButton rdbtnRojo;
	JRadioButton rdbtnAzul;
	JButton buttonBack;
	JButton buttonNext;
	JLabel lblMarca;
	JLabel lblModelo;
	JButton cancelButton;
	JButton btnAnadir;
	JComboBox<Marca> comboBoxMarca;
	JComboBox<Modelo> comboBoxModelo;	
	JButton btnCancelar;
	JButton btnAceptar;
	JPanel panelColor;
	private JPanel panelAceptCancel;
	


	/**
	 * Create the dialog.
	 */
	public VentanaPadre() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(1, 0, 450, 241);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setForeground(Color.DARK_GRAY);
		lblMatricula.setBounds(19, 10, 73, 16);
		contentPanel.add(lblMatricula);

		textFieldMatricula = new JTextField();
		textFieldMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {// Salir del foco
				textFieldMatricula.setText(textFieldMatricula.getText().toUpperCase());
				if (!Fichero.concesionario.checkMatricula(textFieldMatricula.getText())) {
					textFieldMatricula.setForeground(Color.RED);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {// Dentro del foco
				textFieldMatricula.setForeground(Color.BLACK);
			}
		});
		textFieldMatricula.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textFieldMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMatricula.setBounds(19, 32, 402, 37);
		contentPanel.add(textFieldMatricula);
		
		panelColor = new JPanel();
		panelColor.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelColor.setBounds(29, 76, 390, 60);
		contentPanel.add(panelColor);
		panelColor.setLayout(null);
		
		rdbtnPlata = new JRadioButton("plata");
		rdbtnPlata.setBounds(34, 23, 84, 23);
		panelColor.add(rdbtnPlata);
		buttonGroupColors.add(rdbtnPlata);

		rdbtnRojo = new JRadioButton("rojo");
		rdbtnRojo.setBounds(151, 23, 84, 23);
		panelColor.add(rdbtnRojo);
		buttonGroupColors.add(rdbtnRojo);

		rdbtnAzul = new JRadioButton("azul");
		rdbtnAzul.setBounds(273, 23, 84, 23);
		panelColor.add(rdbtnAzul);
		buttonGroupColors.add(rdbtnAzul);

		lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.DARK_GRAY);
		lblMarca.setBounds(36, 146, 61, 16);
		contentPanel.add(lblMarca);

		lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.DARK_GRAY);
		lblModelo.setBounds(279, 147, 61, 16);
		contentPanel.add(lblModelo);

		comboBoxModelo = new JComboBox<Modelo>();
		comboBoxModelo.setBounds(269, 168, 117, 27);
		contentPanel.add(comboBoxModelo);
		comboBoxModelo.removeAllItems();

		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxModelo.removeAllItems();
				for (Modelo modelo : Modelo.values()) {// Rellenar el comboBox
														// de modelo dependiendo
														// de la marca.
					if (modelo.getMarca() == comboBoxMarca.getSelectedItem())
						comboBoxModelo.addItem(modelo);
				}
			}
		});

		comboBoxMarca.setBounds(27, 167, 130, 27);
		comboBoxMarca.setModel(new DefaultComboBoxModel<>(Marca.values()));
		contentPanel.add(comboBoxMarca);
		


		buttonBack = new JButton("<");
		buttonBack.setBounds(125, 203, 73, 24);
		contentPanel.add(buttonBack);
		
		buttonNext = new JButton(">");
		buttonNext.setBounds(249, 203, 73, 24);
		contentPanel.add(buttonNext);
		
		panelAceptCancel = new JPanel();
		panelAceptCancel.setBounds(0, 235, 450, 43);
		getContentPane().add(panelAceptCancel);
		panelAceptCancel.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 8, 117, 29);
		panelAceptCancel.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(214, 8, 117, 29);
		panelAceptCancel.add(btnAceptar);
		
		
	}
	
	concesionarioCochesGUI.estructura.Color getSelectedColor(){
		if (rdbtnPlata.isSelected()) {
			return concesionarioCochesGUI.estructura.Color.PLATA;
		}else if(rdbtnRojo.isSelected()){
			return concesionarioCochesGUI.estructura.Color.ROJO;
		}else if(rdbtnAzul.isSelected()){
			return concesionarioCochesGUI.estructura.Color.AZUL;
		}else{
			return null;
		}
		
	}
	
	void seleccionarColor(concesionarioCochesGUI.estructura.Color color) {
		switch (color) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		}
		
	}

}
