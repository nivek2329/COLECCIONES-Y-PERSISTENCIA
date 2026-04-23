/**
* Clase de prueba del sistema Transmilenio.
* Esta clase se encarga de crear estaciones, rutas y cargar
* la información en el sistema para verificar el correcto
* funcionamiento de los servicios implementados.
* En particular, se prueban los servicios:
* Servicio 1: Tiempo de espera de una estación
* Servicio 2: Rutas del sistema ordenadas alfabéticamente
* Servicio 4: Rutas sin transbordos entre dos estaciones
* @author DOPO TEAM 2026-1
* @version 2.0
*/
public class PruebaTransmilenio {
    
    /**
     * Método principal de ejecución de las pruebas.
     * Crea las estaciones y rutas del sistema, las registra
     * en una instancia de {Transmilenio} y ejecuta
     * pruebas sobre los servicios solicitados en el enunciado.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */

    public static void main(String[] args) {

        // ================================================================
        // ESTACIONES (nombre, ocupacion, tiempoAlto, tiempoMedio, tiempoBajo)
        // ================================================================
        Estacion portalNorte     = new Estacion("Portal Norte",          NivelOcupacion.ALTO,  10, 5, 2);
        Estacion toberin         = new Estacion("Toberín",               NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion cardioInfantil  = new Estacion("Cardio Infantil",       NivelOcupacion.BAJO,   6, 3, 1);
        Estacion mazuren         = new Estacion("Mazurén",               NivelOcupacion.BAJO,   6, 3, 1);
        Estacion heroes          = new Estacion("Héroes",                NivelOcupacion.ALTO,  10, 5, 2);
        Estacion calle72         = new Estacion("Calle 72",              NivelOcupacion.ALTO,  10, 5, 2);
        Estacion calle45         = new Estacion("Calle 45",              NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion calidonia       = new Estacion("Calidonia",             NivelOcupacion.BAJO,   6, 3, 1);
        Estacion terminal        = new Estacion("Terminal",              NivelOcupacion.ALTO,  10, 5, 2);
        Estacion calle80         = new Estacion("Calle 80",              NivelOcupacion.ALTO,  10, 5, 2);
        Estacion minuto          = new Estacion("Minuto de Dios",        NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion polo            = new Estacion("Polo",                  NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion cad             = new Estacion("CAD",                   NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion portalSur       = new Estacion("Portal Sur",            NivelOcupacion.ALTO,  10, 5, 2);
        Estacion general         = new Estacion("General Santander",     NivelOcupacion.BAJO,   6, 3, 1);
        Estacion bosa            = new Estacion("Bosa",                  NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion biblioteca      = new Estacion("Biblioteca Tintal",     NivelOcupacion.BAJO,   6, 3, 1);
        Estacion americas        = new Estacion("Américas",              NivelOcupacion.ALTO,  10, 5, 2);
        Estacion intermedio      = new Estacion("Intermedio",            NivelOcupacion.MEDIO,  8, 4, 2);
        Estacion ricaurte        = new Estacion("Ricaurte",              NivelOcupacion.ALTO,  10, 5, 2);
        Estacion cisneros        = new Estacion("Cisneros",              NivelOcupacion.BAJO,   6, 3, 1);
        Estacion universidades   = new Estacion("Universidades",         NivelOcupacion.ALTO,  10, 5, 2);
        Estacion museoDel20      = new Estacion("Museo del 20 de Julio", NivelOcupacion.BAJO,   6, 3, 1);
        Estacion portalAmericas  = new Estacion("Portal Américas",       NivelOcupacion.ALTO,  10, 5, 2);

        // ================================================================
        // RUTAS
        // ================================================================

        Ruta norteExpress = new Ruta("Norte Express");
        norteExpress.agregarParada(portalNorte);
        norteExpress.agregarParada(toberin);
        norteExpress.agregarParada(heroes);
        norteExpress.agregarParada(calle72);
        norteExpress.agregarParada(calle45);
        norteExpress.agregarParada(cad);

        Ruta norteLocal = new Ruta("Norte Local");
        norteLocal.agregarParada(portalNorte);
        norteLocal.agregarParada(toberin);
        norteLocal.agregarParada(cardioInfantil);
        norteLocal.agregarParada(mazuren);
        norteLocal.agregarParada(heroes);
        norteLocal.agregarParada(calle72);
        norteLocal.agregarParada(calle45);
        norteLocal.agregarParada(calidonia);
        norteLocal.agregarParada(cad);

        Ruta calle80Express = new Ruta("Calle 80 Express");
        calle80Express.agregarParada(terminal);
        calle80Express.agregarParada(calle80);
        calle80Express.agregarParada(polo);
        calle80Express.agregarParada(cad);
        calle80Express.agregarParada(ricaurte);

        Ruta calle80Local = new Ruta("Calle 80 Local");
        calle80Local.agregarParada(terminal);
        calle80Local.agregarParada(calle80);
        calle80Local.agregarParada(minuto);
        calle80Local.agregarParada(polo);
        calle80Local.agregarParada(cad);
        calle80Local.agregarParada(cisneros);
        calle80Local.agregarParada(ricaurte);

        Ruta caracasExpress = new Ruta("Caracas Express");
        caracasExpress.agregarParada(portalSur);
        caracasExpress.agregarParada(general);
        caracasExpress.agregarParada(bosa);
        caracasExpress.agregarParada(americas);
        caracasExpress.agregarParada(cad);
        caracasExpress.agregarParada(universidades);

        Ruta americasDirecta = new Ruta("Américas Directa");
        americasDirecta.agregarParada(portalAmericas);
        americasDirecta.agregarParada(biblioteca);
        americasDirecta.agregarParada(americas);
        americasDirecta.agregarParada(intermedio);
        americasDirecta.agregarParada(cad);
        americasDirecta.agregarParada(ricaurte);

        Ruta calle26Express = new Ruta("Calle 26 Express");
        calle26Express.agregarParada(terminal);
        calle26Express.agregarParada(polo);
        calle26Express.agregarParada(universidades);
        calle26Express.agregarParada(museoDel20);

        Ruta nqsExpress = new Ruta("NQS Express");
        nqsExpress.agregarParada(portalSur);
        nqsExpress.agregarParada(bosa);
        nqsExpress.agregarParada(americas);
        nqsExpress.agregarParada(ricaurte);
        nqsExpress.agregarParada(universidades);

        Ruta carrera10Local = new Ruta("Carrera 10 Local");
        carrera10Local.agregarParada(museoDel20);
        carrera10Local.agregarParada(universidades);
        carrera10Local.agregarParada(cad);
        carrera10Local.agregarParada(calle45);
        carrera10Local.agregarParada(calle72);

        Ruta subaLocal = new Ruta("Suba Local");
        subaLocal.agregarParada(portalNorte);
        subaLocal.agregarParada(toberin);
        subaLocal.agregarParada(heroes);
        subaLocal.agregarParada(polo);
        subaLocal.agregarParada(cad);

        // ================================================================
        // CARGAR EN EL SISTEMA
        // ================================================================
        Transmilenio tm = new Transmilenio();

        tm.agregarEstacion(portalNorte);
        tm.agregarEstacion(toberin);
        tm.agregarEstacion(cardioInfantil);
        tm.agregarEstacion(mazuren);
        tm.agregarEstacion(heroes);
        tm.agregarEstacion(calle72);
        tm.agregarEstacion(calle45);
        tm.agregarEstacion(calidonia);
        tm.agregarEstacion(terminal);
        tm.agregarEstacion(calle80);
        tm.agregarEstacion(minuto);
        tm.agregarEstacion(polo);
        tm.agregarEstacion(cad);
        tm.agregarEstacion(portalSur);
        tm.agregarEstacion(general);
        tm.agregarEstacion(bosa);
        tm.agregarEstacion(biblioteca);
        tm.agregarEstacion(americas);
        tm.agregarEstacion(intermedio);
        tm.agregarEstacion(ricaurte);
        tm.agregarEstacion(cisneros);
        tm.agregarEstacion(universidades);
        tm.agregarEstacion(museoDel20);
        tm.agregarEstacion(portalAmericas);

        tm.agregarRuta(norteExpress);
        tm.agregarRuta(norteLocal);
        tm.agregarRuta(calle80Express);
        tm.agregarRuta(calle80Local);
        tm.agregarRuta(caracasExpress);
        tm.agregarRuta(americasDirecta);
        tm.agregarRuta(calle26Express);
        tm.agregarRuta(nqsExpress);
        tm.agregarRuta(carrera10Local);
        tm.agregarRuta(subaLocal);

        System.out.println("========================================");
        System.out.println("  PRUEBAS DE SERVICIOS - TRANSMILENIO   ");
        System.out.println("========================================");

        // ================================================================
        // SERVICIO 1
        // ================================================================
        probarServicio1(tm, "Portal Norte");  
        probarServicio1(tm, "Terminal");    
        probarServicio1(tm, "CAD");         
        probarServicio1(tm, "Cardio Infantil");   
        probarServicio1(tm, "Estacion Fantasma"); 

        // ================================================================
        // SERVICIO 2
        // ================================================================
        System.out.println("\n--- SERVICIO 2: Rutas ordenadas ---");
        System.out.println("Rutas: " + tm.getRutasOrdenadas());

        // ================================================================
        // SERVICIO 4
        // ================================================================
        probarServicio4(tm, "Portal Norte",     "Héroes");
        probarServicio4(tm, "Terminal",         "Polo");
        probarServicio4(tm, "Portal Norte",     "CAD");
        probarServicio4(tm, "Terminal",         "Héroes");       
        probarServicio4(tm, "Portal Norte",     "Biblioteca Tintal");
        probarServicio4(tm, "Portal Sur",       "Universidades");
    }

    /**
     * Prueba el servicio 1 del sistema Transmilenio.
     * Dado el nombre de una estación, se intenta obtener
     * y mostrar su tiempo de espera. Si la estación no existe,
     * se captura y muestra la excepción correspondiente.
     *
     * @param tm instancia del sistema Transmilenio
     * @param nombre nombre de la estación a consultar
     */

    private static void probarServicio1(Transmilenio tm, String nombre) {
        System.out.println("\n--- SERVICIO 1: Tiempo de espera ---");
        System.out.println("Estación: " + nombre);
        try {
            System.out.println("Tiempo de espera: " + tm.getTiempoEspera(nombre) + " minutos");
        } catch (EstacionNoEncontrada e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }

    /**
     * Prueba el servicio 4 del sistema Transmilenio.
     * Dadas dos estaciones, se consultan las rutas que permiten
     * realizar el recorrido entre ellas sin realizar transbordos.
     * Si alguna estación no existe, se captura la excepción.
     *
     * @param tm instancia del sistema Transmilenio
     * @param e1 nombre de la estación de origen
     * @param e2 nombre de la estación de destino
     */

    private static void probarServicio4(Transmilenio tm, String e1, String e2) {
        System.out.println("\n--- SERVICIO 4: Rutas sin transbordo ---");
        System.out.println("Origen: " + e1 + " → Destino: " + e2);
        try {
            System.out.println("Rutas directas: " + tm.getRutasSinTransbordo(e1, e2));
        } catch (EstacionNoEncontrada e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}