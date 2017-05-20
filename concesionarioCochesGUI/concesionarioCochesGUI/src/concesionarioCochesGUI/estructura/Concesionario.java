package concesionarioCochesGUI.estructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import concesionarioCochesGUI.estructura.exceptions.CocheNoExisteException;
import concesionarioCochesGUI.estructura.exceptions.CocheYaExisteException;
import concesionarioCochesGUI.estructura.exceptions.MatriculaNoValidaException;

/*
 * No pueden existir dos coches con la misma matr�cula en el almac�n del concesinario
 * no puede a�adirse un coche al concecionario con alguno de sus atributos inv�lidos. Han de conocerse todas sus caracter�sticas 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Representa un concesionario de coches.
 * 
 * L�gicamente, no podr� a�adirse un coche inv�lido almac�n del concesinario)
 * 
 * Han de conocerse todas sus caracter�sticas Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Mar�aLourdes
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Almac�n de los coches del concesionario
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capitan";
	private boolean modificado;

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	// P:Por qu� no se necesita que annadir devuelva boolean??????
	// P:Por qu� no se especifican todas las excepciones de forma
	// expl�cita??????
	/**
	 * A�ade un coche al concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a a�adir
	 * @param color
	 *            Color del coche a a�adir
	 * @param modelo
	 *            Modelo del coche a a�adir
	 * @throws Exception
	 *             Si no se ha podido a�adir el coche al concesionario, porque
	 *             ya hay otro con la misma matr�cula o porque faltan datos
	 */
	public void annadir(String matricula, Color color, Modelo modelo) throws Exception {
		// Coche coche = Coche.instanciarCoche(matricula, color, modelo);

		// if (coche == null || almacen.contains(coche))
		// return false;
		// return almacen.add(coche);
		Coche coche = new Coche(matricula, color, modelo);
		if (almacen.contains(coche))
			throw new CocheYaExisteException(
					"El coche ya existe en el concesionario. ");
		almacen.add(coche);
	}

	/**
	 * Elimina un coche del concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida en su formato
	 * @return true si se ha eliminado. false en otro caso
	 * @throws CocheNoExisteException 
	 */
	public Coche eliminar(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		try {
			return almacen.remove(almacen.indexOf(new Coche(matricula)));
		} catch (Exception  e) {
			throw new  CocheNoExisteException("ERROR:No se pudo borrar el coche.");
		}
		
	}

	/**
	 * Devuelve el n�mero de coches en el almac�n del concesionario
	 * 
	 * @return N�mero de coches en el almac�n del concesionario
	 */
	public int size() {
		return almacen.size();
	}
	
	/**
	 * Compruba si la matricula es valida
	 * @return si es correcta o no
	 * */
	public boolean checkMatricula(String matricula){
		if(Coche.patternMatricula.matcher(matricula).matches())
			return true;
		return false;
	}

	/**
	 * Devuelve el Coche del almac�n indicado por la matr�cula
	 * 
	 * @param matricula
	 *            Matr�cula a buscar
	 * @return Coche contenido en el almac�n. null si no existe
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException,
			CocheNoExisteException {
		// Coche coche = Coche.instanciarCoche(matricula);
		// int index = almacen.indexOf(new Coche(matricula));
		// if (index != -1) {
		// P: qu� sucede si el coche no est� en el concesionario?

		try {
			return almacen.get(almacen.indexOf(new Coche(matricula)));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CocheNoExisteException(
					"El coche no esta en el concesionario.");
		}

		// }
		// return null;
	}
	
	public int indexOf(Coche coche){
		return almacen.indexOf(coche);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}
	
	/**
	 * Converte el almacen de coches (arrayList) en una matriz
	 * */
	public String[][] toArray(){
		String[][] array = new String[almacen.size()][3];
		for (int i = 0; i < array.length; i++) {
			array[i][0]=almacen.get(i).getMatricula();
			array[i][1]=String.valueOf(almacen.get(i).getColor());
			array[i][2]=String.valueOf(almacen.get(i).getModelo());
		}
		return array;
	}
	
	/**
	 * Converte un ArrayList de coches en una matriz
	 * */
	public String [][] topArray(ArrayList<Coche> coches){
		String[][] array = new String[coches.size()][3];
		for (int i = 0; i < array.length; i++) {
			array[i][0]=coches.get(i).getMatricula();
			array[i][1]=String.valueOf(coches.get(i).getColor());
			array[i][2]=String.valueOf(coches.get(i).getModelo());
		}
		return array;
	}
	
	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}
	public Iterator<Coche> iterator(){
		return almacen.iterator();
	}

	public java.util.ListIterator<Coche> listIterator(int i) {
		return  almacen.listIterator();
	}

}
