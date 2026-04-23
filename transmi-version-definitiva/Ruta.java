import java.util.LinkedList;
import java.util.List;
/**
* Representa una ruta del sistema Transmilenio.
* Una ruta está definida por un nombre y una lista ordenada
* de paradas (estaciones) por las cuales pasa el bus.
* La secuencia de las paradas es relevante para el cálculo
* del número de paradas entre dos estaciones.
* @author DOPO TEAM 2026-1
* @version 2.0
*/
public class Ruta {
    private String nombre;
    private LinkedList<Estacion> paradas;
    
    
    /**
     * Construye una ruta con el nombre dado.
     *
     * @param nombre nombre de la ruta
     */
    public Ruta(String nombre) {
        this.nombre = nombre;
        this.paradas = new LinkedList<>();
    }

    /**
     * Agrega una estación como parada al final del recorrido de la ruta.
     *
     * @param e estación a agregar como parada
     */

    public void agregarParada(Estacion e) {
        paradas.add(e);
    }

    /**
     * Indica si la ruta contiene una estación dada entre sus paradas.
     *
     * @param e estación a verificar
     * @return true si la estación pertenece a la ruta, false en caso contrario
     */

    public boolean contiene(Estacion e) {
        return paradas.contains(e);
    }

    /**
     * Calcula el número de paradas entre dos estaciones dentro de la ruta.
     * Si alguna estación no pertenece a la ruta, o si el orden de las
     * estaciones no es válido (la estación de origen aparece después
     * o es la misma que la de destino), el método retorna -1.
     *
     * @param e1 estación de origen
     * @param e2 estación de destino
     * @return número de paradas entre las estaciones o -1 si no es válido
     */

    public int numParadas(Estacion e1, Estacion e2) {
        int i1 = paradas.indexOf(e1);
        int i2 = paradas.indexOf(e2);
        if (i1 == -1 || i2 == -1 || i1 >= i2) return -1;
        return i2 - i1;
    }

    /**
     * Retorna el nombre de la ruta.
     *
     * @return nombre de la ruta
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la lista de paradas de la ruta.
     *
     * @return lista de estaciones donde la ruta se detiene
     */

    public List<Estacion> getParadas() {
        return paradas;
    }
}