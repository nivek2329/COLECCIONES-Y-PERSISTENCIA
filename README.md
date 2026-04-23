# Sistema de Gestión Transmilenio

Modelado orientado a objetos de la red de buses de Bogotá, con estaciones, rutas y consultas de servicio.

---

## Tabla de contenido

1. [Descripción general](#descripción-general)
2. [Clases del dominio](#clases-del-dominio)
3. [Colecciones utilizadas y justificación](#colecciones-utilizadas-y-justificación)
4. [Excepciones](#excepciones)
5. [Servicios implementados](#servicios-implementados)

---

## Descripción general

El sistema representa la red del Transmilenio mediante tres entidades principales — `Estacion`, `Ruta` y `Troncal` — orquestadas por la clase fachada `Transmilenio`. El objetivo es responder consultas reales del sistema: tiempo de espera en una estación, listado ordenado de rutas y búsqueda de rutas sin transbordo entre dos puntos.

---

## Clases del dominio

### `Transmilenio` (fachada del sistema)

Punto de entrada único a todos los servicios. Contiene las tres colecciones principales del sistema (`rutas`, `estaciones`, `troncales`) y expone los métodos de consulta. Al centralizar la lógica aquí, el resto de las clases permanecen como entidades puras de datos, lo que facilita las pruebas y esperamos mas adelante la refactorizacion.

### `Estacion`

Representa una estación física de la red. Almacena su nombre, el nivel de ocupación actual (`NivelOcupacion`) y una tabla de tiempos de espera indexada por nivel. 

**Atributos clave:**
- `nombre : String`  identificador único de la estación.
- `ocupacion : NivelOcupacion`  estado actual (ALTO / MEDIO / BAJO).
- `tiemposEspera : EnumMap<NivelOcupacion, Integer>`  minutos de espera según el nivel.

### `Ruta`

Representa una línea del sistema (p. ej. *Norte Express*, *NQS Express*). Mantiene sus paradas en orden de recorrido y ofrece el método `numParadas(e1, e2)`, que devuelve cuántos tramos hay entre dos estaciones en esa ruta (-1 si alguna no existe en la ruta o si el orden es inverso).

**Atributos clave:**
- `nombre : String`  nombre de la ruta.
- `paradas : LinkedList<Estacion>`  estaciones en el orden exacto del recorrido.

### `NivelOcupacion` 

Enumeración con tres constantes: `ALTO`, `MEDIO`, `BAJO`. Al ser enum, el compilador garantiza que no existan valores inválidos y se puede usar como clave eficiente en un `EnumMap`.

### `ManejadorArchivos` 

Clase de utilidad con métodos estáticos: importar una ruta desde archivo, exportar rutas y guardar troncales. Lanza `ArchivoInvalido` ante rutas de archivo inaccesibles.

---

## Colecciones utilizadas y justificación

| Colección | Clase que la usa | Tipo | Justificación |
|-----------|-----------------|------|---------------|
| `rutas` | `Transmilenio` | `TreeMap<String, Ruta>` | El **Servicio 2** debe entregar las rutas ordenadas alfabéticamente. `TreeMap` mantiene las claves siempre ordenadas, por lo que `keySet()` ya es la lista ordenada sin costo adicional. |
| `estaciones` | `Transmilenio` | `HashMap<String, Estacion>` | El **Servicio 1** busca una estación por nombre. No se requiere orden, solo acceso rápido, por lo que `HashMap` es la opción óptima. |
| `paradas` | `Ruta` | `LinkedList<Estacion>` | Las paradas tienen un orden secuencial estricto (el del recorrido). Se necesita `indexOf` para localizar dos estaciones y calcular la distancia entre ellas. `LinkedList` representa fielmente una secuencia lineal, adecuado para el tamaño real de una ruta. |
| `tiemposEspera` | `Estacion` | `EnumMap<NivelOcupacion, Integer>` | La clave es un enum de tres constantes. `EnumMap` usa un array interno indexado por la posición del enum, lo que lo hace más eficiente en memoria y velocidad que un `HashMap` para claves de tipo enum.|
| `resultado` (local) | `Transmilenio` | `ArrayList` + `sort` con `Comparator` | En el **Servicio 4** se construye dinámicamente la lista de rutas válidas. Se usa `ArrayList` para la ordenación con `Comparator` compuesto (primero por número de paradas, luego alfabético). |
| `conteo` (local) | `Transmilenio` | `HashMap<String, Integer>` | Auxiliar del **Servicio 4**: guarda el número de paradas entre e1 y e2 para cada ruta válida, evitando calcular `numParadas` dos veces durante el ordenamiento. |

---

## Excepciones

El sistema usa una jerarquía de excepciones **checked** para obligar al llamador a manejar explícitamente los errores de negocio.


### `TransmilenioException`

Clase base de todas las excepciones del dominio. Extiende `Exception` (checked), lo que obliga a los servicios a declarar `throws` y al cliente a usar `try-catch`. Recibe un mensaje descriptivo que se delega al constructor de `Exception`.

### `EstacionNoEncontrada`

Se lanza en el **Servicio 1** y en el **Servicio 4** cuando se pasa un nombre de estación que no existe en el `HashMap` de `Transmilenio`. Almacena el nombre buscado en el atributo `nombre` para que el cliente pueda mostrarlo o registrarlo sin tener que parsear el mensaje.


### `RutaNoEncontrada`

Se lanza cuando se intenta acceder a una ruta por nombre y no existe en el sistema. Almacena el atributo `path` con el identificador buscado.

### `ArchivoInvalido`

Lanzada por `ManejadorArchivos` cuando la ruta de archivo proporcionada no es accesible o tiene formato incorrecto. Almacena `nombreRuta` con el path problemático.

---

## Servicios implementados

### Servicio 1 `getTiempoEspera(String nombre)`

**Propósito:** Devolver los minutos de espera en una estación dado su nombre.

**Algoritmo:**
1. Busca la estación en el `HashMap<String, Estacion>`.
2. Si no existe, lanza `EstacionNoEncontrada`.
3. Llama a `e.getTiempoEspera()`, que consulta el `EnumMap` con la ocupación actual.

**Complejidad total:**.

---

### Servicio 2  `getRutasOrdenadas()`

**Propósito:** Retornar los nombres de todas las rutas en orden alfabético.

**Algoritmo:**
1. Usa directamente `rutas.keySet()` del `TreeMap`  las claves ya están ordenadas.
2. Envuelve el conjunto en un `ArrayList` para retornar un `List<String>`.

---

### Servicio 4  `getRutasSinTransbordo(String e1, String e2)`

**Propósito:** Listar las rutas que conectan directamente dos estaciones (sin transbordo), ordenadas primero por número de paradas entre ellas (ascendente) y luego alfabéticamente.

**Algoritmo:**
1. Busca `e1` y `e2` en el `HashMap`  cada una; lanza `EstacionNoEncontrada` si alguna falta.
2. Itera sobre todas las rutas (`rutas.values()`) y llama a `r.numParadas(e1, e2)`:
   - `numParadas` usa `indexOf` en la `LinkedList`.
   - Si el resultado es distinto de -1, guarda el par `(nombreRuta, conteo)` en un `HashMap` auxiliar.
3. Construye un `ArrayList` con las claves del `HashMap` y lo ordena con un `Comparator` compuesto: primero por valor de paradas, luego por nombre natural.



**Casos de prueba incluidos:**

| Servicio | Entrada | Resultado esperado |
|----------|---------|-------------------|
| S1 | `"Portal Norte"` (ALTO) | 10 minutos |
| S1 | `"CAD"` (MEDIO) | 5 minutos |
| S1 | `"Cardio Infantil"` (BAJO) | 3 minutos |
| S1 | `"Estacion Fantasma"` | `EstacionNoEncontrada` |
| S2 | — | Lista de 10 rutas en orden alfabético |
| S4 | `"Portal Norte"` → `"Héroes"` | Rutas que las conecten directamente |
| S4 | `"Terminal"` → `"Héroes"` | Lista vacía (sin ruta directa) |
| S4 | `"Portal Sur"` → `"Universidades"` | Rutas directas ordenadas por paradas |
