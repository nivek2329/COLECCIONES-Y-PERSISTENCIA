/**
 * Excepción base del sistema Transmilenio.
 * Esta clase sirve como superclase para las excepciones
 * específicas del dominio del sistema, permitiendo
 * manejar de forma jerárquica los errores relacionados
 * con la lógica del Transmilenio.
 * @author DOPO TEAM 2026-1
 * @version 2.0
 */
public class TransmilenioException extends Exception {
    
    /**
     * Construye una excepción del sistema Transmilenio
     * con un mensaje descriptivo.
     *
     * @param mensaje mensaje que describe la causa del error
     */

    public TransmilenioException(String mensaje) {
        super(mensaje);
    }
}