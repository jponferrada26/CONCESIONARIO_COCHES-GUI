package concesionarioCochesGUI.gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import concesionarioCochesGUI.estructura.Concesionario;
import concesionarioCochesGUI.estructura.Fichero;
import concesionarioCochesGUI.estructura.Filtro;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Ventana principal
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Filtro filtro = new Filtro(".obj", "Objecto");
	private static JFileChooser jFileChooser = new JFileChooser();
	private static Principal frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {//Salir con la equis
				salir();
			}
			@Override
			public void windowClosed(WindowEvent e) {//Salir con Alt+F4
				salir();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/LogoConcesionario.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Sin titulo");
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setMnemonic('a');
		menuBar.add(mnNewMenu);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.concesionario.isModificado()) {
					Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					switch (respuesta) {
					case 0:
						guardarComoFichero();
						Fichero.concesionario = new Concesionario();
						setTitle("Sin titulo");
						Fichero.concesionario.setModificado(false);
						break;
					case 1:

						setTitle("Sin titulo");
						Fichero.concesionario = new Concesionario();
						Fichero.concesionario.setModificado(false);
						break;
					}
				} else {
					setTitle("Sin titulo");
					Fichero.concesionario = new Concesionario();
					Fichero.concesionario.setModificado(false);
				}

			}

		});
		mnNewMenu.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.concesionario.isModificado()) {
					Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					switch (respuesta) {
					case 0://Se desea guardar los cambios
						guardarComoFichero();
						break;
					case 1://Se desea abrir el fichero y por lo tanto guardar los cambios
						try {
							abrirArchivo();
						} catch (HeadlessException | ClassNotFoundException | IOException e1) {
							JOptionPane.showMessageDialog(null, "No se ha podido abrir el concesionario.", "Aceptar",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
				} else {
					try {
						abrirArchivo();
					} catch (HeadlessException | ClassNotFoundException | IOException e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido abrir el concesionario.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnNewMenu.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTitle() == "Sin titulo") {//Comprueba que si no hay abierto ningun concesionario, se guarde el actual
					guardarComoFichero();
					Fichero.concesionario.setModificado(false);
				} else {
					try {
						Fichero.guardar(Fichero.concesionario, jFileChooser.getSelectedFile());
						Fichero.concesionario.setModificado(false);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido guardar el concesionario.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		});
		mnNewMenu.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComoFichero();
				Fichero.concesionario.setModificado(false);
			}
		});
		mnNewMenu.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}

		});
		mnNewMenu.add(mntmSalir);

		JMenu mnNewMenu_1 = new JMenu("Coche");
		mnNewMenu_1.setMnemonic('c');
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// alta coche/añadir
														// coche
				AnadirCoche anadirCoche = new AnadirCoche();
				anadirCoche.setVisible(true);

			}
		});
		mnNewMenu_1.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fichero.concesionario.size()>0){
					BorrarCoche borrarCoche  = new BorrarCoche();
					borrarCoche.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "El concesionario está vacio.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnNewMenu_1.add(mntmBaja);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fichero.concesionario.size()>0){
					MostrarConcesionario mostrar =  new MostrarConcesionario();
					mostrar.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "El concesionario está vacio.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		mnNewMenu_1.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('b');
		menuBar.add(mnBuscar);

		JMenuItem mntmPorMatricula = new JMenuItem("Por matricula");
		mntmPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fichero.concesionario.size()>0){
					BuscarPorMatricula buscarPorMatricula =  new BuscarPorMatricula();
					buscarPorMatricula.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "El concesionario está vacio.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscar.add(mntmPorMatricula);

		JMenuItem mntmPorColor = new JMenuItem("Por color");
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fichero.concesionario.size()>0){
					BuscarPorColor buscarPorColor =  new BuscarPorColor();
					buscarPorColor.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "El concesionario está vacio.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscar.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Acerca de
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmVerAyuda = new JMenuItem("Ver ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ver ayuda
				Ayuda ayuda = Ayuda.getInstance();
				ayuda.setVisible(true);

			}
		});
		mnAyuda.add(mntmVerAyuda);
		mnAyuda.add(mntmAcercaDe);

	}

	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);
		if (jFileChooser.showDialog(jFileChooser, "Abrir") == JFileChooser.APPROVE_OPTION) {
			// Fichero.fichero = abrir.getSelectedFile();
			Fichero.abrir(jFileChooser.getSelectedFile());
			setTitle(jFileChooser.getSelectedFile().getName());
			Fichero.concesionario.setModificado(false);

		}
	}

	private void guardarComoFichero() {
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == jFileChooser.showDialog(jFileChooser, "Guardar")) {
			jFileChooser.setAcceptAllFileFilterUsed(false);

			if (jFileChooser.getSelectedFile().exists()) {
				Object[] opciones = new Object[] { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(frame, "Ya existe.", "¿Deseas sobreescribir los cambios?",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);

				switch (respuesta) {
				case 0:
					try {
						Fichero.guardar(Fichero.concesionario, jFileChooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}

					break;
				default:
					JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
					break;
				}

			} else {
				try {
					Fichero.guardar(Fichero.concesionario, jFileChooser.getSelectedFile());
					JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		Fichero.concesionario.setModificado(false);
	}
	
	/**
	 * 
	 */
	private void salir() {
		if (Fichero.concesionario.isModificado()) {//Comprueba que se ha mododificado el concesionario
			Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
			int respuesta = JOptionPane.showOptionDialog(null,
					"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
			switch (respuesta) {
			case 0://Se desea que se guarde los cambios y se salga del programa
				guardarComoFichero();
				System.exit(0);
				break;
			case 1://Se desea salir del programa sin guardar los cambios
				System.exit(0);
				break;
			}
		} else {
			System.exit(0);
		}
	}
}
