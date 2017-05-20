package concesionarioCochesGUI.estructura;

import java.io.Serializable;
//P: Indica cu�ndo se utiliza la etiqueta @see Busca c�mo se usa en http://docs.oracle.com/javase/7/docs/technotes/tools/solaris/javadoc.html
import java.util.regex.Pattern;

import concesionarioCochesGUI.estructura.exceptions.ColorNoValidoException;
import concesionarioCochesGUI.estructura.exceptions.MatriculaNoValidaException;
import concesionarioCochesGUI.estructura.exceptions.ModeloNoValidoException;

/**
 * Representa a un coche.
 * <p>
 * Un coche tendr� las siguientes caracter�sticas:
 * <ol>
 * <li>Color. Se limitar�n los colores a tres: plata, rojo y azul.</li>
 * 
 * 
 * <li>Modelo. Se limitar�n los modelos de coches a siete: C�rdoba (marca
 * Seat),Toledo (marca Seat),Ibiza (marca Seat), Serie 1 (marca BMW), Serie 2
 * (marca BMW),Serie 3 (marca BMW) y Serie 5 (marca BMW). Para solicitar el
 * modelo al dar de alta al coche podr� implementarse un m�todo pedirModelo que
 * mediante la gesti�n de un men�, devolver� el modelo indicado.</li>
 * 
 * 
 * <li>Matr�cula (�nica por coche) El formato de las matr�culas ser� el nuevo:
 * combinaci�n de cuatro n�meros (de 0000 a 9999) y tres letras, comenzando por
 * BBB y terminando por ZZZ, excluyendo vocales, la LL, la � y la Q.</li>
 * 
 * 
 * <ol>
 * <li>Matr�culas v�lidas: 0000-BBB, 0000 BBB, 0000BBB, 1234ZZZ.</li>
 * <li>Matr�culas inv�lidas: 000_BBB, 9999-BBQ, 0000-B�B, 0000-DEF, 0000 bbb,
 * 0000 AAA</li>
 * </ol>
 * </ol>
 * 
 * @author Mar�aLourdes
 * 
 */
public class Coche implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Matr�cula del coche
	 */
	private String matricula;

	/**
	 * Color del coche
	 */
	private Color color;
	/**
	 * Modelo del coche
	 */
	private Modelo modelo;
	/**
	 * Representaci�n compilada de la matr�cua de un coche.
	 * 
	 * <p>
	 * Seg�n el enunciado del examen: El formato de las matr�culas ser� el
	 * nuevo: combinaci�n de cuatro n�meros (de 0000 a 9999) y tres letras,
	 * comenzando por BBB y terminando por ZZZ, excluyendo vocales, la LL, la �
	 * y la Q.</li>
	 * <ol>
	 * <li>Matr�culas v�lidas: 0000-BBB, 0000 BBB, 0000BBB, 1234ZZZ.</li>
	 * <li>Matr�culas inv�lidas: 000_BBB, 9999-BBQ, 0000-B�B, 0000-DEF, 0000
	 * bbb, 0000 AAA</li>
	 * </ol>
	 */
	public static final Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	// P: Cu�ntas excepciones se lanzan en Coche?? �qu� m�todos se interrumpen?
	// �D�nde se tratan?
	// P: podr�a simplificarse en Exception?
	/**
	 * Crea un nuevo Coche con los par�metros indicados
	 * 
	 * @param matricula
	 *            Matr�cula del nuevo coche
	 * @param color
	 *            Color del nuevo coche
	 * @param modelo
	 *            Modelo del nuevo coche
	 * @throws Exception
	 *             Si el coche no puede crearse, en caso de una matr�cula
	 *             inv�lida, color o modelo.
	 * 
	 * @see Coche#patternMatricula
	 */
	public Coche(String matricula, Color color, Modelo modelo) throws Exception {
		// throws MatriculaNoValidaException, ColorNoValidaException,
		// ModeloNoValidoException {
		super();
		setMatricula(matricula);
		setColor(color);
		setModelo(modelo);
	}

	/**
	 * Crea un nuevo Coche con los par�metros indicados
	 * 
	 * @param matricula
	 *            Matr�cula del nuevo coche
	 * @throws MatriculaNoValidaException
	 *             Si el coche no puede crearse, en caso de una matr�cula
	 *             inv�lida.
	 * @see Coche#patternMatricula
	 */
	public Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	// P:�Por qu� no se usa instanciarCoche??

	// static Coche instanciarCoche(String matricula, Color color, Modelo
	// modelo) {
	// if (esValida(matricula) && color != null && modelo != null)
	// return new Coche(matricula, color, modelo);
	// return null;
	// }

	// static Coche instanciarCoche(String matricula) {
	// if (esValida(matricula))
	// return new Coche(matricula);
	// return null;
	// }
	/**
	 * Indica si una matr�cula es o no v�lida
	 * 
	 * @param matricula
	 * @return true si la matr�cula es v�lida. false en otro caso
	 * 
	 * @see Coche#patternMatricula
	 */
	private static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}

	/**
	 * Asigna la matr�cula del coche
	 * 
	 * @param matricula
	 *            Matr�cula a asignar
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 * @see Coche#patternMatricula
	 */
	private void setMatricula(String matricula)
			throws MatriculaNoValidaException {
		if (esValida(matricula))
			this.matricula = estandarizarMatricula(matricula);
		else
			throw new MatriculaNoValidaException("La matracula no es valida. ");
	}

	/**
	 * Le cambia el formato a la matr�cula del coche. u
	 * 
	 * @param matricula
	 *            Matr�cula a estandarizar. Puede tener un espacio o guion para
	 *            separar los 4 n�meros de las tres letras
	 * @return Matr�cula sin espacios ni guiones
	 */
	private String estandarizarMatricula(String matricula) {
		assert esValida(matricula);
		return matricula.replaceAll("[ -]", "");
	}

	/**
	 * Obtiene el color del coche
	 * 
	 * @return Color del coche
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Asigna el color del coche
	 * 
	 * @param color
	 *            Color a asignar
	 * @throws ColorNoValidoException
	 *             Si el color es null
	 */
	private void setColor(Color color) throws ColorNoValidoException {
		if (color != null)
			this.color = color;
		else
			throw new ColorNoValidoException("El color no es valido. ");
	}

	/**
	 * * Asigna el modelo del coche
	 * 
	 * @param modelo
	 *            Modelo a asignar
	 * @throws ModeloNoValidoException
	 *             Si el Modelo es null
	 */
	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo != null)
			this.modelo = modelo;
		else
			throw new ModeloNoValidoException("El modelo no es valido. ");
	}

	public Modelo getModelo() {
		return modelo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Indica si otro coche es "igual a " este. Para ello, se fija en sus
	 * matr�culas.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/**
	 * Devuelve una representaci�n del coche en forma de cadena.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coche \n-matricula = " + matricula + " \n-color=" + color
				+ " \n-modelo=" + modelo;
	}

}
