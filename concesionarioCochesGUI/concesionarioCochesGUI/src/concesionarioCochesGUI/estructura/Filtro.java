package concesionarioCochesGUI.estructura;


import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter {
 
    private String extension;
    private String description;
    /**
     * Constructor
     * @param extension especificada
     * @param descripcion de la extensión
     */
    public Filtro(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }
    /**
     * Método que comprueba si los archivos son válidos
     * @param file archivo seleccionado
     * @return devuelve true si el archivo contiene la extensión correcta, en caso de que no la lleve
     * se le añadira la extensión
     */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
    }
    /**
     * Método que devuelve la descripción
     * @return devuelve la descripción con respecto a una extensión
     */
    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
    /**
     * Método que devuelve la extensión
     * @return  devuelve la extensión
     */
    public String getExtension() {
        return extension;
    }
    
}
