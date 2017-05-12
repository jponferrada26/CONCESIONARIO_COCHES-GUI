package concesionarioCochesGUI.estructura;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

public class Fichero implements Serializable{
	private static final long serialVersionUID = 1L;
	public static Concesionario concesionario = new Concesionario();
	private static final Pattern EXTENSION = Pattern.compile("^((\\w)+(\\.obj))$");

	
	/**
	 * Abrir
	 * Permite la lectura del fichero al programa.
	 * */
	public static void abrir(File file) throws IOException, ClassNotFoundException{
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			concesionario = (Concesionario)in.readObject();
		}
	}
	
//	public static void nuevo(){
//		setFichero("Sin_titulo");
//	}
	
	
	public static File comprobarFichero(File file){
		if(EXTENSION.matcher(file.getName()).matches())
			return file;
		else{
//			setFichero(file.getAbsolutePath()+".obj");
			return  new File(file.getAbsolutePath()+".obj");
		}
	}
	
	
	
//	/**
//	 * @return fichero
//	 * */
//	public static File getFichero() {
//		return fichero;
//	}
//	
//	/**
//	 * Asignar al fichero uno nuevo con el nombre correcto
//	 * @param fichero
//	 */
//	public static void setFichero(String file) {
//		fichero = new File(file);
//	}

	/**
	 *  Guardar
	 * @param obj
	 * @throws IOException
	 */
	public static void guardar(Object obj, File fichero) throws IOException{
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)))){	
			out.writeObject(obj);	
		}
	}
	
	/**
	 * Guardar como
	 * */
	public static void guardadComo(Object obj,File fichero) throws IOException{
//		fichero = comprobarFichero(file);
		try (ObjectOutputStream out = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(fichero)))){	
			out.writeObject(obj);	
		}
	}
	
	
	
	
}
