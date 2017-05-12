package concesionarioCochesGUI.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
/**
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class Ayuda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static final Ayuda ayuda = new Ayuda();


	public static Ayuda getInstance(){
		return ayuda;
	}
	
	/**
	 * Create the dialog.
	 */
	private Ayuda() {
		setTitle("Ayuda");
		setResizable(false);
		setBounds(100, 100, 331, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea txtrBienvenidoALa = new JTextArea();
		txtrBienvenidoALa.setText("\t\n\n\t-Bienvenido\nEsta es la ayuda del Software Concesionario.\n\nPara cualquier problema avise al técnico:\n\nContacto:\tjponferrada26@gmail.com");
		txtrBienvenidoALa.setEditable(false);
		txtrBienvenidoALa.setBounds(6, 6, 319, 313);
		contentPanel.add(txtrBienvenidoALa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
