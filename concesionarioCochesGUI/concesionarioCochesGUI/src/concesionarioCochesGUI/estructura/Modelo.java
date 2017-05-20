package concesionarioCochesGUI.estructura;

/**
 * Representa los modelos. Seg�n el enunciado del examen:
 * 
 * <pre>
 *  Se limitar�n los modelos de coches a siete: C�rdoba (marca Seat),
 *  Toledo (marca Seat),Ibiza (marca Seat), Serie 1 (marca BMW), Serie 2 (marca BMW),
 *  Serie 3 (marca BMW) y Serie 5 (marca BMW).
 * </pre>
 * 
 * @author Mar�aLourdes
 * 
 */
public enum Modelo {
	/**
	 * El modelo Serie 1
	 */
	SERIE1(Marca.BMW),
	/**
	 * El modelo Serie 2
	 */
	SERIE2(Marca.BMW),
	/**
	 * El modelo Serie 3
	 */
	SERIE3(Marca.BMW),
	/**
	 * El modelo Serie 5
	 */
	SERIE5(Marca.BMW),

	/**
	 * El modelo C�rdoba
	 */
	CORDOBA(Marca.SEAT),

	/**
	 * El modelo Ibiza
	 */
	IBIZA(Marca.SEAT),

	/**
	 * El modelo Toledo
	 */
	TOLEDO(Marca.SEAT);
	/**
	 * Marca del modelo
	 */
	private Marca marca;

	/**
	 * Crea un modelo con la marca indicada
	 * 
	 * @param marca
	 *            Marca del nuevo modelo
	 */
	private Modelo(Marca marca) {
		this.marca = marca;
	}

	/**
	 * Obtiene la marca del modelo
	 * 
	 * @return Marca del modelo
	 */
	public Marca getMarca() {
		return marca;
	}

	/**
	 * Devuelve una representaci�n del modelo en forma de cadena.
	 */
	public String toString() {
		return name();

	}


}
