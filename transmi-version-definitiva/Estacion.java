import java.util.EnumMap;

/**
 * Representa una estación del sistema Transmilenio.
 * Cada estación tiene un nombre y un nivel de ocupación actual.
 * El tiempo de espera de la estación depende de su nivel de ocupación
 * y se obtiene a partir de un conjunto de tiempos asociados a cada nivel. 
 * @author DOPO TEAM 2026-1
 * @version 2.0
 */
public class Estacion {
    private String nombre;
    private NivelOcupacion ocupacion;
    private EnumMap<NivelOcupacion, Integer> tiemposEspera;

    
    /**
     * Construye una estación con su nombre, nivel de ocupación
     * y los tiempos de espera asociados a cada nivel.
     *
     * @param nombre nombre de la estación
     * @param ocupacion nivel de ocupación inicial de la estación
     * @param tiempoAlto tiempo de espera para ocupación alta
     * @param tiempoMedio tiempo de espera para ocupación media
     * @param tiempoBajo tiempo de espera para ocupación baja
     */
    public Estacion(String nombre, NivelOcupacion ocupacion,
                    int tiempoAlto, int tiempoMedio, int tiempoBajo) {
        this.nombre = nombre;
        this.ocupacion = ocupacion;
        tiemposEspera = new EnumMap<>(NivelOcupacion.class);
        tiemposEspera.put(NivelOcupacion.ALTO,  tiempoAlto);
        tiemposEspera.put(NivelOcupacion.MEDIO, tiempoMedio);
        tiemposEspera.put(NivelOcupacion.BAJO,  tiempoBajo);
    }

    
    /**
     * Retorna el tiempo de espera de la estación según
     * su nivel de ocupación actual.
     *
     * @return tiempo de espera en minutos
     */
    public int getTiempoEspera() {
        return tiemposEspera.get(ocupacion);
    }

    
    /**
     * Retorna el nombre de la estación.
     *
     * @return nombre de la estación
     */
    public String getNombre() {
        return nombre;
    }

    
    /**
     * Retorna el nivel de ocupación actual de la estación.
     *
     * @return nivel de ocupación
     */
    public NivelOcupacion getOcupacion() {
        return ocupacion;
    }

    
    /**
     * Actualiza el nivel de ocupación de la estación.
     *
     * @param ocupacion nuevo nivel de ocupación
     */
    public void setOcupacion(NivelOcupacion ocupacion) {
        this.ocupacion = ocupacion;
    }
}