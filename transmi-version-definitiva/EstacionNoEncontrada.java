/**
 * Excepción que se lanza cuando se intenta acceder o consultar
 * una estación que no existe en el sistema Transmilenio.
 * Esta excepción es un tipo específico de {TransmilenioException}
 * y representa una violación de la precondición de los servicios
 * que requieren que la estación esté registrada previamente.
 * @author DOPO TEAM 2026-1
 * @version 2.0
 */
public class EstacionNoEncontrada extends TransmilenioException {
    private String nombre;

    /**
     * Construye la excepción indicando el nombre de la estación
     * que no existe en el sistema.
     *
     * @param nombre nombre de la estación no encontrada
     */

    public EstacionNoEncontrada(String nombre) {
        super("Estacion no encontrada: " + nombre);
        this.nombre = nombre;
    }
    
    /**
     * Retorna el nombre de la estación que causó la excepción.
     *
     * @return nombre de la estación no encontrada
     */

    public String getNombre() {
        return nombre;
    }
}