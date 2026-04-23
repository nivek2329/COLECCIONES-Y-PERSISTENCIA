import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Representa el sistema Transmilenio.
 * Esta clase actúa como fachada del sistema y es responsable
 * de gestionar las estaciones y rutas, así como de ofrecer
 * los servicios solicitados en el enunciado del problema.
 * Entre los servicios implementados se encuentran:
 * Consulta del tiempo de espera de una estación
 * Listado de rutas ordenadas alfabéticamente
 * Consulta de rutas sin transbordos entre dos estaciones
 * @author DOPO TEAM 2026-1
 * @version 2.0
 */

public class Transmilenio {

    private TreeMap<String, Ruta>     rutas;
    private HashMap<String, Estacion> estaciones;

    
    /**
     * Construye un sistema Transmilenio vacío,
     * inicializando las colecciones de rutas y estaciones.
     */
    public Transmilenio() {
        rutas      = new TreeMap<>();
        estaciones = new HashMap<>();
    }

    
    /**
     * Agrega una estación al sistema.
     *
     * @param e estación a agregar
     */
    public void agregarEstacion(Estacion e) {
        estaciones.put(e.getNombre(), e);
    }

    
    /**
     * Agrega una ruta al sistema.
     *
     * @param r ruta a agregar
     */
    public void agregarRuta(Ruta r) {
        rutas.put(r.getNombre(), r);
    }

    
    /**
     * Servicio 1.
     * Retorna el tiempo de espera de una estación dado su nombre.
     * El tiempo de espera depende del nivel de ocupación
     * asociado a la estación.
     *
     * @param nombreEstacion nombre de la estación a consultar
     * @return tiempo de espera en minutos
     * @throws EstacionNoEncontrada si la estación no existe en el sistema
     */
    public int getTiempoEspera(String nombreEstacion)
            throws EstacionNoEncontrada {
        Estacion e = estaciones.get(nombreEstacion);
        if (e == null) {
            throw new EstacionNoEncontrada(nombreEstacion);
        }
        return e.getTiempoEspera();
    }

    
    /**
     * Servicio 2.
     * Retorna los nombres de las rutas del sistema
     * ordenados alfabéticamente.
     *
     * @return lista de nombres de rutas ordenados
     */
    public List<String> getRutasOrdenadas() {
        return new ArrayList<>(rutas.keySet());
    }

    
    /**
     * Servicio 4.
     * Retorna las rutas que permiten ir de una estación a otra
     * sin realizar transbordos.
     * Las rutas se retornan ordenadas según:
     * Menor número de paradas
     * Orden alfabético por nombre de ruta
     *
     * @param nombreE1 nombre de la estación de origen
     * @param nombreE2 nombre de la estación de destino
     * @return lista de nombres de rutas que permiten el recorrido sin transbordos
     * @throws EstacionNoEncontrada si alguna estación no existe en el sistema
     */
    public List<String> getRutasSinTransbordo(String nombreE1,
                                               String nombreE2)
            throws EstacionNoEncontrada {

        Estacion e1 = estaciones.get(nombreE1);
        if (e1 == null) throw new EstacionNoEncontrada(nombreE1);

        Estacion e2 = estaciones.get(nombreE2);
        if (e2 == null) throw new EstacionNoEncontrada(nombreE2);

        HashMap<String, Integer> conteo = new HashMap<>();

        for (Ruta r : rutas.values()) {
            int n = r.numParadas(e1, e2);
            if (n != -1) {
                conteo.put(r.getNombre(), n);
            }
        }

        List<String> resultado = new ArrayList<>(conteo.keySet());
        resultado.sort(Comparator
                .comparingInt((String nombre) -> conteo.get(nombre))
                .thenComparing(Comparator.naturalOrder()));

        return resultado;
    }
}